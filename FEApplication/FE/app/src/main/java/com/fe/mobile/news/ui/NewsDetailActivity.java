package com.fe.mobile.news.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.fe.mobile.Helper;
import com.fe.mobile.R;
import com.fe.mobile.news.New;
import com.fe.mobile.util.TrackingScrollView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by dgarcia on 27/04/2015.
 */
public class NewsDetailActivity extends ActionBarActivity
{
    private New newItem;
    private Toolbar mToolbar;
    //private FavDbAdapter mDbHelper;

    ImageLoader imageLoader;

    WebView htmlTextView;
    private ImageView thumb;
    private TextView mTitle;
    private int mImageHeight;
    boolean FadeBar = true;
    int latestAlpha;

    Long id;
    String link;
    String title;
    String dateauthor;
    String content;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        mToolbar.setTitle("Noticias");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageLoader = Helper.initializeImageLoader(this);

        thumb = (ImageView) findViewById(R.id.image);

        Bundle bundle = this.getIntent().getExtras();

            newItem = (New) this.getIntent().getSerializableExtra("newItem");
            if (newItem != null) {
                id = newItem.getId();
                title = newItem.getTitulo();
                link = newItem.getUrl();
                dateauthor = "Publicado el " + newItem.getDate() + " por " + newItem.getAutor();
                content=newItem.getContenido();

                //getting a valid url, displaying it and setting a parralax listener. Also a fallback for no image.
                System.out.println("Bundle newItem : "+newItem.toString());

                String imageurl = newItem.getThumbnailUrl();
                if (null == imageurl || imageurl.equals("")){
                    imageurl = newItem.getThumbnailUrl();
                }
                if (null == imageurl || imageurl.equals("")){
                    thumb.getLayoutParams().height = (getActionBarHeight());
                    FadeBar = false;

                } else {
                    System.out.println("imageUrl => thumb : "+imageurl);
                    imageLoader.displayImage(imageurl, thumb);

                    mImageHeight = thumb.getLayoutParams().height;

                    /*((TrackingScrollView) findViewById(R.id.scroller)).setOnScrollChangedListener(
                            new TrackingScrollView.OnScrollChangedListener() {
                                @Override
                                public void onScrollChanged(TrackingScrollView source, int l, int t, int oldl, int oldt) {
                                    handleScroll(source, t);
                                }
                            }
                    );
                    */
                }
            }



        //if content at all
        if (null != newItem || null != bundle) {

            mTitle = (TextView) findViewById(R.id.title);
            mTitle.setText(title);

            TextView mDateAuthorView = (TextView) findViewById(R.id.dateauthorview);
            mDateAuthorView.setText(dateauthor);

            TextView mContent=(TextView)findViewById(R.id.contentNew);
            mContent.setText(content);

            /*
            htmlTextView = (WebView) findViewById(R.id.context);
            htmlTextView.getSettings().setJavaScriptEnabled(true);
            htmlTextView.setBackgroundColor(Color.argb(1, 0, 0, 0));
            htmlTextView.getSettings().setDefaultFontSize(WebHelper.getWebViewFontSize(this));
            htmlTextView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            htmlTextView.setWebViewClient(new WebViewClient(){
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    if (url != null && (url.startsWith("http://") || url.startsWith("http://"))) {
                        Intent mIntent = new Intent(WordpressDetailActivity.this, WebviewActivity.class);
                        mIntent.putExtra(WebviewActivity.URL, url);
                        startActivity(mIntent);
                        return true;
                    } else {
                        Uri uri = Uri.parse(url);
                        Intent ViewIntent = new Intent(Intent.ACTION_VIEW, uri);

                        // Verify it resolves
                        PackageManager packageManager = getPackageManager();
                        List<ResolveInfo> activities = packageManager.queryIntentActivities(ViewIntent, 0);
                        boolean isIntentSafe = activities.size() > 0;

                        // Start an activity if it's safe
                        if (isIntentSafe) {
                            startActivity(ViewIntent);
                        }
                        return true;
                    }
                }
            });*/
        }

        //fresh from the web or old from favorites?
        /*if (id != null){
            new DownloadFilesTask().execute(url, id);
        } else if (content != null) {
            Log.v("INFO", "content: " + content);
            thumb.getLayoutParams().height = (getActionBarHeight());
            FadeBar = false;
            setHTML(content);
        }*/

        /*if (FadeBar){
            mToolbar.getBackground().setAlpha(0);
            Helper.setStatusBarColor(NewsDetailActivity.this, getResources().getColor(R.color.black));
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }*/

       /*Button btnFav = (Button) findViewById(R.id.favoritebutton);

        //Listening to button event
        btnFav.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                mDbHelper = new FavDbAdapter(WordpressDetailActivity.this);
                mDbHelper.open();

                if(mDbHelper.checkEvent(title, content, dateauthor, link, "", "", "wordpress")) {
                    // Item is new
                    mDbHelper.addFavorite(title, content, dateauthor, link, "", "", "wordpress");
                    Toast toast = Toast.makeText(WordpressDetailActivity.this, getResources().getString(R.string.favorite_success), Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(WordpressDetailActivity.this, getResources().getString(R.string.favorite_duplicate), Toast.LENGTH_LONG);
                    toast.show();
                }
            }
        });*/
    }

    private void handleScroll(TrackingScrollView source, int top) {
        int scrolledImageHeight = Math.min(mImageHeight, Math.max(0, top));

        ViewGroup.MarginLayoutParams imageParams = (ViewGroup.MarginLayoutParams) thumb.getLayoutParams();
        int newImageHeight = mImageHeight - scrolledImageHeight;
        if (imageParams.height != newImageHeight) {
            // Transfer image height to margin top
            imageParams.height = newImageHeight;
            imageParams.topMargin = scrolledImageHeight;

            // Invalidate view
            thumb.setLayoutParams(imageParams);
        }

        if (FadeBar){
            final int imageheaderHeight = thumb.getHeight() - getSupportActionBar().getHeight();
            //t=how far you scrolled
            //ratio is from 0,0.1,0.2,...1
            final float ratio = (float) Math.min(Math.max(top, 0), imageheaderHeight) / imageheaderHeight;
            //setting the new alpha value from 0-255 or transparent to opaque
            final int newAlpha = (int) (ratio * 255);
            if (newAlpha != latestAlpha){
                mToolbar.getBackground().setAlpha(newAlpha);
                Helper.setStatusBarColor(NewsDetailActivity.this, blendColors(ratio, this));
            }

            latestAlpha = newAlpha;
        }
    }


    @Override
    public void onPause(){
        super.onPause();
        mToolbar.getBackground().setAlpha(255);
    }

    @Override
    public void onResume(){
        super.onPause();
       /* if (FadeBar)
            mToolbar.getBackground().setAlpha(latestAlpha);
            */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.news_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.menu_share: {

                shareContent();
                return true;
            }
           /* case R.id.menu_view:
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(link));
                startActivity(intent);
                return true;
            */
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void shareContent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, newItem.getTitulo() + "\n" +newItem.getUrl());
        sendIntent.setType("text/plain");
        startActivity(Intent.createChooser(sendIntent, "Compartir"));
    }



    private int getActionBarHeight() {
        int actionBarHeight = getSupportActionBar().getHeight();
        if (actionBarHeight != 0)
            return actionBarHeight;
        final TypedValue tv = new TypedValue();
        if (getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
        return actionBarHeight;
    }

    private static int blendColors(float ratio, Context c) {
        int color1 = c.getResources().getColor(R.color.myPrimaryDarkColor);
        int color2 = c.getResources().getColor(R.color.black);
        final float inverseRation = 1f - ratio;
        float r = (Color.red(color1) * ratio) + (Color.red(color2) * inverseRation);
        float g = (Color.green(color1) * ratio) + (Color.green(color2) * inverseRation);
        float b = (Color.blue(color1) * ratio) + (Color.blue(color2) * inverseRation);
        return Color.rgb((int) r, (int) g, (int) b);
    }



}