package com.contus.keerthi.myapp.dbEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.contus.keerthi.myapp.Contract.MyApp;
import com.contus.keerthi.myapp.POJO.News;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



/**
 * Created by user on 22/2/17.
 */

public class dbHelper extends SQLiteOpenHelper {

    private final static String TAG = "dbHelper";
    public dbHelper(Context context,String db_name,int db_version)
    {
        super(context,db_name,null,db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String news_table_query = "CREATE TABLE IF NOT EXISTS "+ MyApp.news.TABLE_NAME+" ( "+
                MyApp.news.COLUMN_NAME_SOURCE + " TEXT,"+MyApp.news.COLUMN_NAME_TITLE+" TEXT,"+
                MyApp.news.COLUMN_NAME_AUTHOR + " TEXT,"+MyApp.news.COLUMN_NAME_URL+" TEXT,"+
                MyApp.news.COLUMN_NAME_IMAGE_URL+ " TEXT," + MyApp.news.COLUMN_NAME_DES +" TEXT, "
                + MyApp.news.COLUMN_NAME_PUBLISHEDAT+" DATETIME );";

        db.execSQL(news_table_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+MyApp.news.TABLE_NAME);
    }

    public boolean insertNewsRow(News news)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MyApp.news.COLUMN_NAME_SOURCE,news.getSource());
        values.put(MyApp.news.COLUMN_NAME_TITLE,news.getTitle());
        values.put(MyApp.news.COLUMN_NAME_DES,news.getDes());
        values.put(MyApp.news.COLUMN_NAME_AUTHOR,news.getAuthor());
        values.put(MyApp.news.COLUMN_NAME_URL,news.getUrl());
        values.put(MyApp.news.COLUMN_NAME_IMAGE_URL,news.getImage_url());
        values.put(MyApp.news.COLUMN_NAME_PUBLISHEDAT, news.getDate());
        long n = sqLiteDatabase.insert(MyApp.news.TABLE_NAME,null,values);
        sqLiteDatabase.close();
        return n>0;
    }

    public ArrayList<News> getNews(){

        Date To = new Date();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE,-3);
        Date From = cal.getTime();

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<News> newsList = null;
        Cursor cursor = null;
        try {
            newsList = new ArrayList<News>();

            Log.i(TAG, "getNews: before raw query ");
            
            String selectQuery = "SELECT * FROM "+MyApp.news.TABLE_NAME;

            Log.i(TAG, "getNews: query"+selectQuery);
            cursor = db.rawQuery(selectQuery,null);
            Log.i(TAG, "getNews: after raw query");
            if(cursor.moveToFirst())
            {
                do{
                    News news= new News();
                    news.setSource(cursor.getString(cursor.getColumnIndex(MyApp.news.COLUMN_NAME_SOURCE)));
                    news.setAuthor(cursor.getString(cursor.getColumnIndex(MyApp.news.COLUMN_NAME_AUTHOR)));
                    news.setTitle(cursor.getString(cursor.getColumnIndex(MyApp.news.COLUMN_NAME_TITLE)));
                    news.setDes(cursor.getString(cursor.getColumnIndex(MyApp.news.COLUMN_NAME_DES)));
                    news.setUrl(cursor.getString(cursor.getColumnIndex(MyApp.news.COLUMN_NAME_URL)));
                    news.setImage_url(cursor.getString(cursor.getColumnIndex(MyApp.news.COLUMN_NAME_IMAGE_URL)));
                    news.setDate(cursor.getString(cursor.getColumnIndex(MyApp.news.COLUMN_NAME_PUBLISHEDAT)));
                    newsList.add(news);
                }while(cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            cursor.close();
            db.close();
        }

        return newsList;
    }

    public int getCount(String query){
        SQLiteDatabase db=this.getReadableDatabase();
        try {
            Cursor cursor=db.rawQuery(query,null);
            return cursor.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }finally {
            db.close();
        }
    }
}
