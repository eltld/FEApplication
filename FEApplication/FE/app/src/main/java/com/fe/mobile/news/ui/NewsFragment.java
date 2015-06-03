package com.fe.mobile.news.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.fe.mobile.Helper;
import com.fe.mobile.MainActivity;
import com.fe.mobile.R;
import com.fe.mobile.ServiceHandler;
import com.fe.mobile.db.dao.NewDao;
import com.fe.mobile.news.New;
import com.fe.mobile.news.NewAdapter;
import com.fe.mobile.news.NewJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by David Garcia on 23/04/2015.
 */
public class NewsFragment extends Fragment {

    private ArrayList<New> newsList = null;
    private ArrayList<NewJson> newsJson=null;
    private ListView feedListView = null;
    private View footerView;
    private Activity mAct;
    private NewAdapter newAdapter = null;

    private Long lastIdInsert;


    private LinearLayout ll;
    RelativeLayout pDialog;

    /** Configuration idNew ***/
    Integer idNew=0;

    Integer pages;
    String perpage = "15";
    Integer curpage = 1;

    String apiurl;
    String baseurl;
    String searchurl;
    String searchurlend;
    String pageurl;

    Boolean isLoading = false;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout linear layoutof fragment_news
        ll = (LinearLayout) inflater.inflate(R.layout.fragment_news_list, container, false);
        setHasOptionsMenu(true);


        apiurl = this.getArguments().getString(MainActivity.DATA);

        baseurl=apiurl;


        footerView = inflater.inflate(R.layout.listview_footer, null);
        feedListView= (ListView) ll.findViewById(R.id.custom_list);
        feedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,	long id) {
                Object o = feedListView.getItemAtPosition(position);
                New newItem  = (New) o;
                System.out.println("onItemClick : "+newItem.toString());

                Intent intent = new Intent(mAct, NewsDetailActivity.class);
                intent.putExtra("newItem", newItem);
                //intent.putExtra("apiurl", apiurl);
                startActivity(intent);
            }
        });
        feedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {


                if (newAdapter == null)
                    return ;

                if (newAdapter.getCount() == 0)
                    return ;

               int l = visibleItemCount + firstVisibleItem;
                pages=0;
                /*if (l >= totalItemCount && !isLoading && curpage <= pages) {
                    System.out.println("new more data");
                    new DownloadFilesTask(baseurl, false).execute();
                }*/
                if (l >= totalItemCount && !isLoading) {
                    System.out.println("l : "+l + "totalItemCount : "+totalItemCount);
                    baseurl="http://10.2.0.3/phpUnju2/noticias2.php?IdNew="+lastIdInsert;

                    new DownloadFilesTask(baseurl, false).execute();
                }
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
        });

        return ll;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAct = getActivity();

        System.out.println(" antes del donwload files  => baseurl : "+baseurl);
        new DownloadFilesTask(baseurl, true).execute();
    }



    public void updateList(boolean initialload) {
         System.out.println("updateList : "+initialload);
        NewDao newDao=new NewDao(this.getActivity());
        newDao.addList(newsList);

        if (initialload){
            newAdapter = new NewAdapter(mAct, 0,0, newsList);
            System.out.println("updateList : "+newAdapter);
            feedListView.setAdapter(newAdapter);
        } else {
            newAdapter.addAll(newsList);
            newAdapter.notifyDataSetChanged();
        }
    }


    private class DownloadFilesTask extends AsyncTask<String, Integer, Void> {

        String url;
        boolean initialload;

        DownloadFilesTask(String url, boolean firstload){
            this.url = url;
            this.initialload = firstload;
        }

        @Override
        protected void onPreExecute() {
            if (isLoading){
                this.cancel(true);
            } else {
                isLoading = true;
            }
            if (initialload){
                pDialog = (RelativeLayout) ll.findViewById(R.id.progressBarHolder);

                if (pDialog.getVisibility() == View.GONE) {
                    pDialog.setVisibility(View.VISIBLE);
                    feedListView.setVisibility(View.GONE);
                }

                curpage = 1;

                if (null != newsList){
                    newsList.clear();
                }
                if (null != feedListView){
                    feedListView.setAdapter(null);
                }
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    feedListView.addFooterView(footerView);
                }
            } else {
                feedListView.addFooterView(footerView);
            }
        }

        @Override
        protected void onPostExecute(Void result) {

            System.out.println("onPostExecute : "+newsList.size());
            if (null != newsList) {
                updateList(initialload);
            } else {
                Helper.noConnection(mAct, true);
            }
            if (pDialog.getVisibility() == View.VISIBLE) {
                pDialog.setVisibility(View.GONE);
                //feedListView.setVisibility(View.VISIBLE);
                Helper.revealView(feedListView,ll);

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    feedListView.removeFooterView(footerView);
                }
            } else {
                feedListView.removeFooterView(footerView);
            }
            isLoading = false;
        }

        @Override
        protected Void doInBackground(String... params) {
               Log.v("INFO", "Step 0, started");
            // getting JSON string from URL

            System.out.println("url => "+url);

            // TODO Auto-generated method stub
            ServiceHandler serviceHandler = new ServiceHandler();
            try {
                String jsonString = serviceHandler.makeServiceCall(
                        url, serviceHandler.GET);

                Gson gson=new Gson();
                //toJson
                Type tipoList = new TypeToken<List<NewJson>>(){}.getType();
                newsJson=new ArrayList<NewJson>();
                newsJson= gson.fromJson(jsonString,tipoList);
                newsList=new ArrayList<New>();
                 for(NewJson json : newsJson)
                 {
                     New newItem=new New();
                     newItem.setId(json.getNoticia_id());
                     newItem.setTitulo(json.getNoticia_titulo());
                     newItem.setAutor("Tito");
                     newItem.setDate(json.getNoticia_fecha());
                     newItem.setAttachmentUrl(json.getNoticia_url_image());
                     newItem.setContenido(json.getNoticia_cuerpo());

                     String tmp=json.getNoticia_url_image();
                     tmp=tmp.replace("./imgnotis/","");


                     newItem.setThumbnailUrl("http://10.2.0.3/noticias/imgnotis/"+tmp);

                     newsList.add(newItem);
                 }

               //almaceno el ultimo id de la noticia
                System.out.println("Last id insert : "+newsJson.get(newsList.size()-1).getNoticia_id());
               lastIdInsert= newsJson.get(newsJson.size()-1).getNoticia_id();


            }catch (Exception ex) {

                System.out.println("ex  : "+ex.toString());

            }
                // se comento esta lineaJSONObject json = getJSONFromUrl(url);
            Log.v("INFO", "Step 2, got JsonObjoct");
            //parsing json data
            //parseJson(json);
            return null;
        }
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       System.out.println("Refrescar");

        inflater.inflate(R.menu.refresh_menu, menu);

        //set & get the search button in the actionbar
        final SearchView searchView = new SearchView(mAct);

        searchView.setQueryHint(getResources().getString(R.string.video_search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    query = URLEncoder.encode(query, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                searchView.clearFocus();

                baseurl = searchurl + query + searchurlend;
                new DownloadFilesTask(baseurl, true).execute();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });


        searchView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {

            @Override
            public void onViewDetachedFromWindow(View arg0) {
                if (!isLoading){
                    baseurl = pageurl;
                    new DownloadFilesTask(baseurl, true).execute();
                }
            }

            @Override
            public void onViewAttachedToWindow(View arg0) {
                // search was opened
            }
        });

        //TODO make menu an xml item
        menu.add("search")
                .setIcon(R.drawable.ic_action_search)
                .setActionView(searchView)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM|MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.refresh:
                if (!isLoading){
                    baseurl = apiurl;
                    new DownloadFilesTask(baseurl, true).execute();
                } else {
                    Toast.makeText(mAct, getString(R.string.already_loading), Toast.LENGTH_LONG).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
