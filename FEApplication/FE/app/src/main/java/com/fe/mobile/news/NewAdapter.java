package com.fe.mobile.news;

import android.annotation.SuppressLint;
import android.content.Context;
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
 * Created by manuonda on 23/04/2015.
 */
public class NewAdapter extends ArrayAdapter<New>
{

    private ArrayList<New> listData;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private String TAG_TOP = "TOP";



    public NewAdapter(Context context, int resource, int textViewResourceId, ArrayList<New> listData) {
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
    public New getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @SuppressLint("InflateParams")
    public View getView(int position, View convertView, ViewGroup parent) {

        New newsItem = (New) listData.get(position);
        ImageLoader imageLoader = Helper.initializeImageLoader(mContext);

        //Aca especifica si es la primera posicion de la noticia
        //lo pone como banner

        //if it is the first item, give a special treatment.
        if (position == 0 && (null != newsItem.getAttachmentUrl() && !newsItem.getAttachmentUrl().equals(""))){
            convertView = layoutInflater.inflate(R.layout.listview_highlight, null);
            imageLoader.displayImage(newsItem.getThumbnailUrl(), (ImageView) convertView.findViewById(R.id.imageViewHighlight));
            ((TextView) convertView.findViewById(R.id.textViewHighlight)).setText(newsItem.getTitulo());
            //convierte la imagen en TOP
            convertView.setTag(TAG_TOP);
            return convertView;
        }

        ViewHolder holder;
        if (convertView == null || convertView.getTag().equals(TAG_TOP)) {
            convertView = layoutInflater.inflate(R.layout.fragment_news_list_row, null);
            holder = new ViewHolder();
            holder.headlineView = (TextView) convertView.findViewById(R.id.title);
            holder.reportedDateView = (TextView) convertView.findViewById(R.id.date);
            holder.imageView = (ImageView) convertView.findViewById(R.id.thumbImage);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.imageView.setImageBitmap(null);
        }



        holder.headlineView.setText(newsItem.getTitulo());
        holder.reportedDateView.setText((CharSequence) newsItem.getDate());

        if (null == newsItem.getThumbnailUrl() || newsItem.getThumbnailUrl().equals("")){
            holder.imageView.setVisibility(View.GONE);
        } else {
            holder.imageView.setVisibility(View.VISIBLE);
            imageLoader.displayImage(newsItem.getThumbnailUrl(), holder.imageView);
        }


        return convertView;

    }

    static class ViewHolder {
        TextView headlineView;
        TextView reportedDateView;
        ImageView imageView;
    }
}
