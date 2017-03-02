package com.contus.keerthi.myapp.DbEntry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.contus.keerthi.myapp.Contract.MyApp;
import com.contus.keerthi.myapp.Pojo.Gallery;
import com.contus.keerthi.myapp.Pojo.News;

import java.util.ArrayList;


/**Separate Database class for all the classes
 * Created by Keerthivasan on 22/2/17.
 */

public class dbHelper extends SQLiteOpenHelper {

    private final static String TAG = "dbHelper";
    public dbHelper(Context context,String db_name,int db_version)
    {
        super(context,db_name,null,db_version);
    }

    /**
     * databse and table creation
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        String news_table_query = "CREATE TABLE IF NOT EXISTS "+ MyApp.news.TABLE_NAME+" ( "+
                MyApp.news.COLUMN_NAME_SOURCE + " TEXT,"+MyApp.news.COLUMN_NAME_TITLE+" TEXT,"+
                MyApp.news.COLUMN_NAME_AUTHOR + " TEXT,"+MyApp.news.COLUMN_NAME_URL+" TEXT,"+
                MyApp.news.COLUMN_NAME_IMAGE_URL+ " TEXT," + MyApp.news.COLUMN_NAME_DES +" TEXT, "+
                MyApp.news.COLUMN_NAME_NEWS_TYPE+ " TEXT,"+
                MyApp.news.COLUMN_NAME_PUBLISHEDAT+" DATETIME "+ " );";
        /**
         * query created
         */

        String gallery_table_query ="CREATE TABLE IF NOT EXISTS "+ MyApp.Gallery.TABLE_NAME+" ( "+
                MyApp.Gallery.COLUMN_IMAGE_SRC + " TEXT,"+MyApp.Gallery.COLUMN_IMAGE_TITLE+" TEXT)";

        db.execSQL(news_table_query);
        db.execSQL(gallery_table_query);
    }

    /**
     * Versions
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+MyApp.news.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+MyApp.Gallery.TABLE_NAME);
    }

    /**
     * Insert statement fop news
     * @param news
     * @return
     */
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
        values.put(MyApp.news.COLUMN_NAME_NEWS_TYPE,news.getType());
        long n = sqLiteDatabase.insert(MyApp.news.TABLE_NAME,null,values);
        sqLiteDatabase.close();
        return n>0;
    }

    /**
     * Insert query for images
     * @param gallery
     * @return
     */
    public boolean insertGalleryRow(Gallery gallery)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(MyApp.Gallery.COLUMN_IMAGE_SRC,gallery.getImageUrl());
        values.put(MyApp.Gallery.COLUMN_IMAGE_TITLE,gallery.getImageTitle());
        long n = sqLiteDatabase.insert(MyApp.Gallery.TABLE_NAME,null,values);
        sqLiteDatabase.close();
        return n>0;
    }

    /**
     * select statement for gallery table
     * @return
     */
    public ArrayList<Gallery> getImages(){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Gallery> imageList = null;
        Cursor cursor = null;
        try {
            imageList = new ArrayList<Gallery>();

            String selectQuery = "SELECT * FROM "+MyApp.Gallery.TABLE_NAME ;
            cursor = db.rawQuery(selectQuery,null);
            if(cursor.moveToFirst())
            {
                do{
                    Gallery gallery= new Gallery();
                    gallery.setImageUrl(cursor.getString(cursor.getColumnIndex(MyApp.Gallery.COLUMN_IMAGE_SRC)));
                    gallery.setImageTitle(cursor.getString(cursor.getColumnIndex(MyApp.Gallery.COLUMN_IMAGE_TITLE)));

                    imageList.add(gallery);
                }while(cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            db.close();
        }
        return imageList;
    }

    /**
     * select statement for News table
     * @param type
     * @return
     */
    public ArrayList<News> getNews(String type){

        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<News> newsList = null;
        Cursor cursor = null;
        try {
            newsList = new ArrayList<News>();
            
            String selectQuery = "SELECT * FROM "+MyApp.news.TABLE_NAME + " WHERE "
                    +MyApp.news.COLUMN_NAME_NEWS_TYPE+" = '"+type+"' ORDER BY rowid DESC";
            cursor = db.rawQuery(selectQuery,null);
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
                    news.setType(cursor.getString(cursor.getColumnIndex(MyApp.news.COLUMN_NAME_NEWS_TYPE)));
                    newsList.add(news);
                }while(cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            db.close();
        }

        return newsList;
    }

    /**
     * cursor to get count from db
     * @param query
     * @return
     */
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
