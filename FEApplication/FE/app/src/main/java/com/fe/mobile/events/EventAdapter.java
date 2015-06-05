package com.fe.mobile.events;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fe.mobile.Helper;
import com.fe.mobile.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by dgarcia on 06/05/2015.
 */
public class EventAdapter extends ArrayAdapter<Event>
{

    private ArrayList<Event> listData;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private String TAG_TOP = "TOP";



    public EventAdapter(Context context, int resource, int textViewResourceId, ArrayList<Event> listData) {
        super(context, resource, listData);
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
    }


    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Event getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {

        Event eventItem = (Event) listData.get(position);

        ImageLoader imageLoader = Helper.initializeImageLoader(mContext);

        //Aca especifica si es la primera posicion de la noticia
        //lo pone como banner
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.fragment_event_list_row, parent, false);
        }

        Event event = listData.get(position);

        if (event != null) {

            TextView name = (TextView) convertView.findViewById(R.id.nameEvent);
            TextView username = (TextView) convertView.findViewById(R.id.usernameEvent);
            ImageView imagem = (ImageView) convertView.findViewById(R.id.profile_image);
            TextView message = (TextView) convertView.findViewById(R.id.messageEvent);
            TextView data = (TextView) convertView.findViewById(R.id.dateEvent);

            name.setText(event.getTitulo());
            username.setText(event.getUsername());
            //username.setTag(tweet.getusername());
            //BitmapManager.getInstance().loadBitmap(tweet.geturlProfileImage(), imagem);
            message.setText(Html.fromHtml(event.getMessage()));

            //message.setTag(tweet.getTweetId());
            ///data.setText(tweet.getData());
        }

        return convertView;

    }

    static class ViewHolder {
        TextView headlineView;
        TextView reportedDateView;
        ImageView imageView;
    }
}