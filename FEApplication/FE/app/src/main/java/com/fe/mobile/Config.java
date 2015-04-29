package com.fe.mobile;

import com.fe.mobile.news.ui.NewsFragment;

import java.util.ArrayList;
import java.util.List;

public class Config {
	
	public static List<NavItem> configuration() {
		
		List<NavItem> i = new ArrayList<NavItem>();
        
		//DONT MODIFY ABOVE THIS LINE
		
		i.add(new NavItem("Seccion", NavItem.SECTION));
        i.add(new NavItem("Noticias", R.drawable.ic_details, NavItem.ITEM, NewsFragment.class, "http://10.2.2.113/WebUNJu/php/noticias2.php?IdNew=null"));

        /*i.add(new NavItem("Uploaded Videos", R.drawable.ic_details, NavItem.ITEM, VideosFragment.class, "UU7V6hW6xqPAiUfataAZZtWA,UC7V6hW6xqPAiUfataAZZtWA"));
        i.add(new NavItem("Liked Videos", R.drawable.ic_details, NavItem.ITEM, VideosFragment.class, "LL7V6hW6xqPAiUfataAZZtWA"));
        
        i.add(new NavItem("News", R.drawable.ic_details, NavItem.ITEM, RssFragment.class, "http://feeds.feedburner.com/AndroidPolice"));
        i.add(new NavItem("Tip Us", R.drawable.ic_details, NavItem.ITEM, WebviewFragment.class, "http://www.androidpolice.com/contact/"));
        
        i.add(new NavItem("Recent Posts", R.drawable.ic_details, NavItem.ITEM, WordpressFragment.class, "http://androidpolice.com/api/"));
        i.add(new NavItem("Cat: Conservation", R.drawable.ic_details, NavItem.ITEM, WordpressFragment.class, "http://moma.org/wp/inside_out/api/,conservation"));
        
        i.add(new NavItem("Wallpaper Tumblr", R.drawable.ic_details, NavItem.ITEM, TumblrFragment.class, "androidbackgrounds"));
        
        i.add(new NavItem("3FM Radio", R.drawable.ic_details, NavItem.ITEM, MediaFragment.class, "http://yp.shoutcast.com/sbin/tunein-station.m3u?id=709809"));
        i.add(new NavItem("Official Twitter", R.drawable.ic_details, NavItem.ITEM, TweetsFragment.class, "Android"));
        i.add(new NavItem("Maps", R.drawable.ic_details, NavItem.ITEM, MapsFragment.class, "drogisterij"));
        
        //It's Suggested to not change the content below this line
        
        i.add(new NavItem("Device", NavItem.SECTION));
        i.add(new NavItem("Favorites", R.drawable.ic_action_favorite, NavItem.EXTRA, FavFragment.class, null));
        i.add(new NavItem("Settings", R.drawable.ic_action_settings, NavItem.EXTRA, SettingsFragment.class, null));
        */
        //DONT MODIFY BELOW THIS LINE
        
        return i;
			
	}
	
}