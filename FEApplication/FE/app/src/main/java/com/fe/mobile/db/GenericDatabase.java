package com.fe.mobile.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by MITO on 21/05/2015.
 */
public class GenericDatabase  {

    private static final String TAG = "GenericDAO";
    private static SQLiteDatabase db;
    private static String dName;
    private static String tName;
    private static String sql;
    public static final String KEY_ID = "_id";

    //private static GenericDAO instance;

    /*
    private GenericDAO(Context ctx, String dbName, String sql, String tableName, int ver){
        super(ctx, dbName, null, ver);
        Log.i(TAG, "Creating or opening database [ " + dbName + " ].GenericDao");
        GenericDAO.sql = sql;
        dName = dbName;
        tName = tableName;
    }

    public static GenericDAO getInstance(Context ctx, String dbName, String sql, String tableName, int ver){
        if(instance == null){
            instance = new GenericDAO(ctx, dbName, sql, tableName, ver);
            try{
                Log.i(TAG, "Creating or opening the database [ " + dbName + " ].");
                db = instance.getWritableDatabase();
            }catch(SQLiteException se){
                Log.e(TAG, "Cound not create and/or open the database [ " + dbName + " ] that will be used for reading and writing.", se);
            }
        }

        return instance;
    }

    public void close(){
        if(instance != null){
            Log.i(TAG, "Closing the database [ " + dName + " ].");
            db.close();
            instance = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db){


        Log.i(TAG, "Trying to create database table if it isn't existed [ " + sql + " ].");
        try{
            db.execSQL(sql);
        }catch(SQLException se){
            Log.e(TAG, "Cound not create the database table according to the SQL statement [ " + sql + " ].", se);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.i(TAG, "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + tName);
        onCreate(db);
    }

    public long insert(String table, ContentValues values){
        return db.insert(table, null, values);
    }

    public Cursor get(String table, String[] columns){
        return db.query(table, columns, null, null, null, null, null);
    }

    public Cursor get(String table, String[] columns,String columnSearch ,long id){
        Cursor cursor =db.query(true, table, columns, columnSearch + "=" + id, null, null, null, null, null);
        if (cursor != null) {

            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor get(String table, String[] columns,String columnSearch ,String columnSearch2,long id,long id2){
        Cursor cursor =db.query(true, table, columns, columnSearch + "=" + id  +" and "+ columnSearch2+"="+id2, null, null, null, null, null);
        if (cursor != null) {

            cursor.moveToFirst();
        }
        return cursor;
    }

    public int delete(String table) {
        return db.delete(table, "1", null);
    }

    public int delete(String table,String columnSearch, long id) {
        return db.delete(table, columnSearch+ "=" + id, null);
    }

    public int update(String table, long id, ContentValues values) {
        return db.update(table, values, KEY_ID + "=" + id, null);
    }

    public int update(String table, String columnSearch,long id, ContentValues values) {
        return db.update(table, values, columnSearch + "=" + id, null);
    }
    */
}



