package com.fe.mobile;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class MainActivity extends ActionBarActivity
        implements NavDrawerCallback{



    private Toolbar mToolbar;
    private NavDrawerFragment mNavigationDrawerFragment;

    public static String DATA = "transaction_data";

    SharedPreferences prefs;
    String mWebUrl = null;
    boolean openedByBackPress = false;


    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

       */

        boolean newDrawer = getResources().getBoolean(R.bool.newdrawer);

        if (newDrawer == true){
            setContentView(R.layout.activity_main_alternate);
        } else {
            setContentView(R.layout.activity_main);
            Helper.setStatusBarColor(MainActivity.this, getResources().getColor(R.color.myPrimaryDarkColor));
        }

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //navigation Drawer Fragment
        mNavigationDrawerFragment = (NavDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_drawer);

        if (newDrawer == true){
            mNavigationDrawerFragment.setup(R.id.scrimInsetsFrameLayout, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
            mNavigationDrawerFragment.getDrawerLayout().setStatusBarBackgroundColor(
                    getResources().getColor(R.color.myPrimaryDarkColor));
        } else {
            mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        }

        prefs = PreferenceManager
                .getDefaultSharedPreferences(getBaseContext());


        //setting push enabled
        /*String push = getString(R.string.rss_push_url);
        if (null != push && !push.equals("")){
            // Create object of SharedPreferences.
            boolean firstStart = prefs.getBoolean("firstStart", true);

            if (firstStart){

                final ServiceStarter alarm = new ServiceStarter();

                SharedPreferences.Editor editor= prefs.edit();

                alarm.setAlarm(this);
                //now, just to be sure, where going to set a value to check if notifications is really enabled
                editor.putBoolean("firstStart", false);
                //commits your edits
                editor.commit();
            }

        }

        //Checking if the user would prefer to show the menu on start
        boolean checkBox = prefs.getBoolean("menuOpenOnStart", false);
        if (checkBox == true && null == mWebUrl){
            mNavigationDrawerFragment.openDrawer();
        }

        */
        // New imageloader
        Helper.initializeImageLoader(this);


    }

    @Override
    public void onNavigationDrawerItemSelected(int position, NavItem item) {
        // update the main content by replacing fragments
        Fragment fragment;
        try {
            fragment = item.getFragment().newInstance();
            if (fragment != null && null == mWebUrl) {
                //adding the data
                Bundle bundle = new Bundle();
                String extra = item.getData();
                bundle.putString(DATA, extra);
                fragment.setArguments(bundle);

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit();

                setTitle(item.getText());

                if (null != MainActivity.this.getSupportActionBar() && null != MainActivity.this.getSupportActionBar().getCustomView()){
                    MainActivity.this.getSupportActionBar().setDisplayOptions(
                            ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
                }

            } else {
                // error in creating fragment
                Log.e("MainActivity", "Error in creating fragment");
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
       /* FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
         */
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }



    @Override
    public void onBackPressed() {
       //back del dispostivo mobile
        if (mNavigationDrawerFragment.isDrawerOpen()){
            mNavigationDrawerFragment.closeDrawer();
        }
    }


}
