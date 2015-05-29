package com.fe.mobile.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MITO on 21/05/2015.
 */
public class ActivityDbHelper extends SQLiteOpenHelper {

    private static SQLiteDatabase db ;

    public ActivityDbHelper(Context context) {
        super(context, ConstantDatabase.DATABASE_NAME, null, ConstantDatabase.DATABASE_VERSION);
        db=getReadableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ConstantDatabase.QUERY_CREATE_NOTICIA);
        db.execSQL(ConstantDatabase.QUERY_CREATE_COMEDOR);
        db.execSQL(ConstantDatabase.QUERY_CREATE_APPLICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
         db.execSQL(ConstantDatabase.QUERY_DROP_NOTICIA);
        db.execSQL(ConstantDatabase.QUERY_DROP_COMEDOR);
       db.execSQL(ConstantDatabase.QUERY_DROP_EVENTO);
        db.execSQL(ConstantDatabase.QUERY_DROP_APPLICATION);


        onCreate(db);
    }
}
