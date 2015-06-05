package com.fe.mobile.events.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fe.mobile.db.ConstantDatabase;
import com.fe.mobile.db.FeDatabaseOpenHelper;
import com.fe.mobile.events.Event;

import java.util.List;

/**
 * Created by dgarcia on 04/06/2015.
 */
public class EventDao {

    //TABLE EVENTO
    public static final String T_EVENTO="T_EVENTO";
    public static final String EVE_ID="id_eve";
    public static final String EVE_TITULO="eve_titulo";
    public static final String EVE_DATE="eve_date";
    public static final String EVE_USERNAME="eve_username";
    public static final String EVE_MESSAGE="eve_message";
    public static final String EVE_URL="eve_url";
    public static final String EVE_URLIMAGE="eve_imageurl";


    FeDatabaseOpenHelper feDbHelper;
    SQLiteDatabase myDb;
    String[] columns = new String[] {
            EVE_ID,
            EVE_TITULO,
            EVE_DATE,
            EVE_USERNAME,
            EVE_MESSAGE,
            EVE_URL,
            EVE_URLIMAGE
    };

    public EventDao(Context context)
    {
        feDbHelper=new FeDatabaseOpenHelper(context);

    }

    public void add(Event eventItem)
    {
        myDb=feDbHelper.openDatabase();
        ContentValues args = new ContentValues();
        args.put(EVE_ID,eventItem.getIdEvent());
        args.put(EVE_TITULO, eventItem.getTitulo());
        args.put(EVE_DATE, eventItem.getDate());
        args.put(EVE_USERNAME, eventItem.getUsername());
        args.put(EVE_MESSAGE,eventItem.getMessage());
        args.put(EVE_URL,eventItem.getUrl());
        args.put(EVE_URLIMAGE,eventItem.getUrlImageDate());
        myDb.insert(ConstantDatabase.T_EVENTO,null,args);

        myDb.close();

    }


    public void update(Event eventItem)
    {

    }


    public void delete(Event newItem)
    {

    }


    public Event getItem(Long idEvent) {

        myDb=feDbHelper.openDatabase();
        String selectQuery = "SELECT * FROM " + T_EVENTO + "  WHERE " + EVE_ID+ "=" + idEvent;
        Event eventItem = null;
        Cursor c = myDb.rawQuery(selectQuery, null);

        if (c != null) {
            if (c.moveToFirst()) {
                eventItem=new Event();
                eventItem.setIdEvent(c.getLong(c.getColumnIndex(EVE_ID)));
                eventItem.setTitulo(c.getString(c.getColumnIndex(EVE_TITULO)));
                eventItem.setDate(c.getString(c.getColumnIndex(EVE_DATE)));
                eventItem.setUsername(c.getString(c.getColumnIndex(EVE_USERNAME)));
                eventItem.setMessage(c.getString(c.getColumnIndex(EVE_MESSAGE)));
                eventItem.setUrl(c.getString(c.getColumnIndex(EVE_URL)));
                eventItem.setUrlImageDate(c.getString(c.getColumnIndex(EVE_URLIMAGE)));
            }

        }else
            eventItem=null;

        myDb.close();

        return eventItem;
    }



    /**
     * add list events
     * @param eventList
     */
    public void addList(List<Event> eventList)
    {

        for(int i=0;i<eventList.size();i++)
        {
            Event eventItem=eventList.get(i);

            Event eventExist=getItem(eventItem.getIdEvent());

            if(eventExist==null) {
                //no existe agrego el evento
                System.out.println("No existe el evento");
                add(eventItem);
            }
            else {
                System.out.println("existe el evento : "+eventItem.getIdEvent());
                update(eventItem);  //actualizo el evento
            }
        }
    }




}
