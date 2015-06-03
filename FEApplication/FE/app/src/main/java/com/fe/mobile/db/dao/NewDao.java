package com.fe.mobile.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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


    public New getItem(Long idNew) {

        myDb=feDbHelper.openDatabase();
        String selectQuery = "SELECT * FROM " + ConstantDatabase.T_NEW + "  WHERE " + ConstantDatabase.NEW_ID + "=" + idNew;
        New newItem = new New();
        Cursor c = myDb.rawQuery(selectQuery, null);

        if (c != null) {
            if (c.moveToFirst()) {
                newItem.setId(c.getLong(c.getColumnIndex(ConstantDatabase.NEW_ID)));
                newItem.setTitulo(c.getString(c.getColumnIndex(ConstantDatabase.NEW_TITULO)));
                newItem.setDate(c.getString(c.getColumnIndex(ConstantDatabase.NEW_FECHA)));
                newItem.setAutor(c.getString(c.getColumnIndex(ConstantDatabase.NEW_AUTOR)));
                newItem.setContenido(c.getString(c.getColumnIndex(ConstantDatabase.NEW_CUERPO)));
                newItem.setUrl(c.getString(c.getColumnIndex(ConstantDatabase.NEW_URL)));

            }

        }else
        newItem=null;

         myDb.close();
         return newItem;
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

            if(newExiste==null) {
               //no existe agrego la noticia
               System.out.println("No existe la noticia");
                add(newItem);
            }
             else {
                System.out.println("Existe la noticia");
                update(newItem);  //actualizo la noticia
            }
          }
    }


}
