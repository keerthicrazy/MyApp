package com.contus.keerthi.myapp.custom;

import android.content.Context;
import android.util.Log;

import com.contus.keerthi.myapp.Contract.MyApp;
import com.contus.keerthi.myapp.Network.Async;
import com.contus.keerthi.myapp.POJO.News;
import com.contus.keerthi.myapp.dbEntry.dbHelper;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;



/**
 * Created by user on 22/2/17.
 */

public class GetNews {

    ArrayList<News> newsArrayList;
    Context context;
    dbHelper dbHelper;
    private final static String TAG = "GetNews";

    public GetNews(Context context)
    {
        newsArrayList = new ArrayList<News>();
        this.context = context;
        dbHelper=new dbHelper(context,MyApp.DB_NAME,MyApp.DATABASE_VERSION);
    }

    public ArrayList<News> getNews()
    {
        String count_query = "SELECT title FROM "+MyApp.news.TABLE_NAME+" WHERE "
                +MyApp.news.COLUMN_NAME_PUBLISHEDAT+" = date('now','localtime')";
        int flag = dbHelper.getCount(count_query);
        if(flag<=0)
            downloadNews();
        newsArrayList=dbHelper.getNews();
        return newsArrayList;
    }

    public void downloadNews()
    {
        final SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");

        try {
            String news_string = new Async().execute(MyApp.API.NEWS_API).get();

            JSONObject jsonObject = new JSONObject(news_string);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");

            for(int i = 0;i<jsonArray.length();i++){
                JSONObject temp = jsonArray.getJSONObject(i);

                String date = parser.format(new Date());

                News news = new News(jsonObject.getString("source").trim(),temp.getString("author").trim(),
                        temp.getString("title").trim(),temp.getString("description").trim(),temp.getString("url"),temp.getString("urlToImage"),date);
                dbHelper.insertNewsRow(news);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
