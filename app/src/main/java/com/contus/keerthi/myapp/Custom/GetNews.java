package com.contus.keerthi.myapp.Custom;

import android.content.Context;

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
 * Created by Keerthivasan on 22/2/17.
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

    /**
     * Method for Get news and its returns the News Array List
     * @param type
     * @return
     */
    public ArrayList<News> getNews(String type)
    {
        String newsType = type;
                /* Database Query for Get the record count from Sqlite Database*/
        String count_query = "SELECT title FROM "+MyApp.news.TABLE_NAME+" WHERE "
                +MyApp.news.COLUMN_NAME_PUBLISHEDAT+" = date('now','localtime') and "
                +MyApp.news.COLUMN_NAME_NEWS_TYPE+" = '"+newsType+"'";
        int flag = dbHelper.getCount(count_query);
        if(flag<=0)
            downloadNews(newsType);
        newsArrayList=dbHelper.getNews(newsType);
        return newsArrayList;
    }

    /**
     * method for download json and parsing and inserting into database
     * @param type
     */
    public void downloadNews(String type)
    {
        final SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        String TempApi;
        String qtype = type;
        if(qtype.equals("latest"))
            TempApi = MyApp.API.NEWS_API_LATEST;
        else if(qtype.equals("top"))
            TempApi = MyApp.API.NEWS_API_TOP;
        else
            TempApi = MyApp.API.NEWS_API_TECH;
        try {
            String news_string = new Async().execute(TempApi).get();

            JSONObject jsonObject = new JSONObject(news_string);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");

            for(int i = 0;i<jsonArray.length();i++){
                JSONObject temp = jsonArray.getJSONObject(i);

                String date = parser.format(new Date());

                News news = new News(jsonObject.getString("source").trim(),temp.getString("author").trim(),
                        temp.getString("title").trim(),temp.getString("description").trim(),temp.getString("url"),temp.getString("urlToImage"),date,qtype);
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
