package com.fe.mobile.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.fe.mobile.db.ConstantDatabase;
import com.fe.mobile.db.FeDatabaseOpenHelper;
import com.fe.mobile.news.New;

import java.util.List;

/**
 * Created by dgarcia on 21/05/2015.
 * Clase correspondiente a newDao de noticias
 * con sql
 */
public class NewDao {

    private New newItem;
    String result="";
    Cursor cursor = null;
    FeDatabaseOpenHelper feDbHelper;
    SQLiteDatabase myDb;
    String[] columns = new String[] {
            ConstantDatabase.NEW_ID,
            ConstantDatabase.NEW_TITULO,
            ConstantDatabase.NEW_FECHA,
            ConstantDatabase.NEW_AUTOR,
            ConstantDatabase.NEW_CUERPO,
            ConstantDatabase.NEW_URL
    };

    public NewDao(Context context)
    {

        System.out.println("NewDao context");
        feDbHelper=new FeDatabaseOpenHelper(context);

    }

    public void add(New newItem)
    {
        myDb=feDbHelper.openDatabase();

        ContentValues args = new ContentValues();
        args.put(ConstantDatabase.NEW_ID,newItem.getId());
        args.put(ConstantDatabase.NEW_TITULO, newItem.getTitulo());
        args.put(ConstantDatabase.NEW_FECHA, newItem.getDate());
        args.put(ConstantDatabase.NEW_AUTOR, newItem.getAutor());
        args.put(ConstantDatabase.NEW_CUERPO,newItem.getContenido());
        args.put(ConstantDatabase.NEW_URL,newItem.getUrl());

        myDb.insert(ConstantDatabase.T_NEW,null,args);

        myDb.close();

    }


    public void update(New newItem)
    {

    }


    public void delete(New newItem)
    {

    }


    public New getItem(Long idNew)
    {

        String selectQuery = "SELECT * FROM "+ConstantDatabase.T_NEW+"  WHERE "+ ConstantDatabase.NEW_ID+"="+idNew;


        Cursor c = myDb.rawQuery(selectQuery, null);

        if (c != null)
         {
           if(c.moveToFirst())
           {


               
           }

            c.moveToFirst();

        New newItem = new New();

        .setId(c.getInt(c.getColumnIndex(KEY_ID)));
        td.setNote((c.getString(c.getColumnIndex(KEY_TODO))));
        td.setCreatedAt(c.getString(c.getColumnIndex(KEY_CREATED_AT)));

        return td;
           }
    }


    /**
     * add list noticia
     * @param newList
     */
    public void addList(List<New> newList)
    {

        for(int i=0;i<newList.size();i++)
        {
            New newItem=newList.get(i);

            New newExiste=getItem(newItem.getId());

            if(newExiste==null) //no existe agrego la noticia
              add(newItem);
            else
              update(newItem);  //actualizo la noticia
        }
    }


}
