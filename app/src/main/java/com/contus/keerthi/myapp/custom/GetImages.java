package com.contus.keerthi.myapp.custom;

import android.content.Context;

import com.contus.keerthi.myapp.Contract.MyApp;
import com.contus.keerthi.myapp.Network.Async;
import com.contus.keerthi.myapp.POJO.Gallery;
import com.contus.keerthi.myapp.dbEntry.dbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by user on 23/2/17.
 */

public class GetImages {
    ArrayList<Gallery> imageArrayList;
    Context context;
    dbHelper dbHelper;

    public GetImages(Context context)
    {
        imageArrayList = new ArrayList<Gallery>();
        this.context = context;
        dbHelper=new dbHelper(context,MyApp.DB_NAME,MyApp.DATABASE_VERSION);
    }

    public ArrayList<Gallery> getImages()
    {
        String count_query = "SELECT "+MyApp.Gallery.COLUMN_IMAGE_TITLE+" FROM "+MyApp.Gallery.TABLE_NAME;
        int flag = dbHelper.getCount(count_query);
        if(flag<=0)
            downloadImages();
        imageArrayList=dbHelper.getImages();
        return imageArrayList;
    }

    public void downloadImages()
    {
        try {
            String image_string = new Async().execute(MyApp.API.FLICKR_IMAGE_URL).get();
            JSONObject jsonObject = new JSONObject(image_string).getJSONObject("photos");
            JSONArray jsonArray = jsonObject.getJSONArray("photo");
            for(int i=0;i<=jsonArray.length();i++)
            {
                JSONObject temp = jsonArray.getJSONObject(i);
                String imgUrl = GenerateImageURL(temp.getString("farm"),temp.getString("server"),temp.getString("id"),temp.getString("secret"));
                Gallery gallery = new Gallery(imgUrl,temp.getString("title"));
                dbHelper.insertGalleryRow(gallery);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
        }
       // return  imageArrayList;
    }

    private String GenerateImageURL(String farm_id,String server_id,String id,String secret) {
        StringBuilder sb = new StringBuilder();

        sb.append("https://farm");
        sb.append(farm_id);
        sb.append(".staticflickr.com/");
        sb.append(server_id);
        sb.append("/");
        sb.append(id);
        sb.append("_");
        sb.append(secret);
        sb.append(".jpg");

        return sb.toString();

    }
}
