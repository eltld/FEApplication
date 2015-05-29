package com.fe.mobile.events.ui;

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
import com.fe.mobile.events.Event;
import com.fe.mobile.events.EventAdapter;
import com.fe.mobile.events.EventJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by David Garcia on 06/05/2015.
 */
public class EventFragment extends Fragment {


    private ArrayList<Event> eventsList = null;
    private ArrayList<EventJson> eventJson =null;
    private ListView feedListView = null;
    private View footerView;
    private Activity mAct;
    private EventAdapter eventAdapter = null;

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
        ll = (LinearLayout) inflater.inflate(R.layout.fragment_event_list, container, false);
        setHasOptionsMenu(true);

        System.out.println("apiurl");
        apiurl = this.getArguments().getString(MainActivity.DATA);
        System.out.println("apiurl "+apiurl);
        baseurl=apiurl;

        footerView = inflater.inflate(R.layout.listview_footer, null);
        feedListView= (ListView) ll.findViewById(R.id.events_list);
        feedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position,	long id) {
                Object o = feedListView.getItemAtPosition(position);
                Event eventItem  = (Event) o;
                System.out.println("onItemClick : "+eventItem.toString());

                Intent intent = new Intent(mAct, EventDetailActivity.class);
                intent.putExtra("eventItem", eventItem);
                startActivity(intent);
            }
        });
        feedListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                System.out.println("setOnScrollListener");
                System.out.println("fistVisibleItem : "+ firstVisibleItem + "visibleItemCount : "+
                        visibleItemCount+ " totalItemCount : "+totalItemCount);

                if (eventAdapter == null)
                    return ;

                if (eventAdapter.getCount() == 0)
                    return ;

                System.out.println("isLoading : "+isLoading);
                int l = visibleItemCount + firstVisibleItem;
                pages=0;
                /*if (l >= totalItemCount && !isLoading && curpage <= pages) {
                    System.out.println("new more data");
                    new DownloadFilesTask(baseurl, false).execute();
                }*/
                if (l >= totalItemCount && !isLoading) {
                    baseurl="http://10.2.2.113/WebUNJu/php/eventos2.php?IdEvento=1";
                    System.out.println("new more data");
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
        if (initialload){
            eventAdapter = new EventAdapter(mAct, 0,0,  eventsList);
            feedListView.setAdapter(eventAdapter);
        } else {
            eventAdapter.addAll(eventsList);
            eventAdapter.notifyDataSetChanged();
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

                if (null !=  eventsList){
                     eventsList.clear();
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

            System.out.println("onPostExecute : " + eventsList.size());
            if (null !=  eventsList) {
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
            //String url = params[0];
            //url = url + Integer.toString(curpage);
            //curpage = curpage + 1;

            Log.v("INFO", "Step 0, started");
            // getting JSON string from URL

            System.out.println("url => "+url);

            // TODO Auto-generated method stub
            ServiceHandler serviceHandler = new ServiceHandler();
            try {
                String jsonString = serviceHandler.makeServiceCall(
                        url, serviceHandler.GET);
                Log.d("JSON", jsonString);
                Gson gson=new Gson();
                //toJson
                Type tipoList = new TypeToken<List<EventJson>>(){}.getType();
                eventJson =new ArrayList<EventJson>();
                eventJson = gson.fromJson(jsonString,tipoList);
                //parse to String
                eventsList=new ArrayList<Event>();
                for(EventJson json : eventJson)
                {
                    Event eventItem=new Event();
                    eventItem.setIdEvent(json.getEvent_idEvent());
                    //nombre evento
                    eventItem.setName(json.getEvent_name());
                    //date del evento
                    eventItem.setDate(json.getEvent_date());
                    //message event
                    eventItem.setMessage(json.getEvent_message());
                    //username
                    eventItem.setUsername(json.getEvent_username());



                    /*String tmp=json.getNoticia_url_image();
                    tmp=tmp.replace("./imgnotis/","");
                    System.out.println("tmp : "+tmp);
                    */

                    //newItem.setThumbnailUrl("http://10.2.0.3/noticias/imgnotis/"+tmp);

                     eventsList.add(eventItem);
                }
                System.out.println("newsJons size : "+ eventJson.size());

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
                    baseurl = pageurl;
                    new DownloadFilesTask(baseurl, true).execute();
                } else {
                    Toast.makeText(mAct, getString(R.string.already_loading), Toast.LENGTH_LONG).show();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
