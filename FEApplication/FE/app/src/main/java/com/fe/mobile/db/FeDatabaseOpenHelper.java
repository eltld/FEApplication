package com.fe.mobile.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by MITO on 21/05/2015.
 */
public class FeDatabaseOpenHelper extends SQLiteOpenHelper {



    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.fe.mobile/databases/";
    private static final String DB_NAME = "fe.db";
    private static final int DB_VERSION=1;
    private SQLiteDatabase myDataBase;
    private Context context;

    public FeDatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    public SQLiteDatabase openDatabase() {

        System.out.println("OpenDatabase");

        File dbFile = context.getDatabasePath(DB_NAME);

        if (!checkDataBase()) { //si no existe la database
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }else
        {
            System.out.println("Exist datatbase");
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }


    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase() {
        boolean dbExist;
        try {

            dbExist = checkDataBase();

        } catch (SQLiteException e) {

            e.printStackTrace();
            throw new Error("database dose not exist");

        }

        if(dbExist){
            //do nothing - database already exist
        }else{
            System.out.println("dbExist no existe database");
            try {
                copyDatabase();
            } catch (IOException e) {

                e.printStackTrace();
                throw new Error("Error copying database");

            }
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){


        SQLiteDatabase checkDB = null;

        try {

            File database=context.getDatabasePath(DB_PATH+DB_NAME);

            if (database.exists()) {

                Log.i("Database", "Found");

                String myPath = database.getAbsolutePath();

                Log.i("Database Path", myPath);

                checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

            } else {

                // Database does not exist so copy it from assets here
                Log.i("Database", "Not Found");

            }

        } catch(SQLiteException e) {

            Log.i("Database", "Not Found");

        } finally {

            if(checkDB != null) {

                checkDB.close();

            }

        }

        return checkDB != null ? true : false;


    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    /*private void copyDataBase() throws IOException{

        //copyDataBase();
        //Open your local db as the input stream
       // InputStream myInput = context.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH +"/"+ DB_NAME;
        File databaseFile = new File( DB_PATH);
        // check if databases folder exists, if not create one and its subfolders
        if (!databaseFile.exists()){
            Log.i("File","No existe dir");
            databaseFile.mkdir();
        }
        else
        {
            Log.i("File","Existe directorio");

        }

        //Open the empty db as the output stream
        /*OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
        */


    /*}*/



    private  void copyDatabase () throws IOException{
        System.out.println("copyDatabase ");
        AssetManager assetManager = context.getResources().getAssets();
        InputStream in = null;
        OutputStream out = null;


        // Path to the just created empty db
        String outFileName = DB_PATH +"/"+ DB_NAME;
        File databaseFile = new File( DB_PATH);
        // check if databases folder exists, if not create one and its subfolders
        if (!databaseFile.exists()){
            Log.i("File","No existe dir");
            databaseFile.mkdir();
        }
        else
        {
            Log.i("File","Existe directorio");

        }

        try {
            in = assetManager.open(DB_NAME);
            out = new FileOutputStream(DB_PATH+DB_NAME);
            byte[] buffer = new byte[1024];
            int read = 0;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
        } catch (IOException e) {
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        //setDatabaseVersion();
        //mInvalidDatabaseFile = false;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
