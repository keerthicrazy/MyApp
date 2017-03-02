package com.contus.keerthi.myapp.Custom;

import android.content.Context;

import com.contus.keerthi.myapp.Contract.MyApp;
import com.contus.keerthi.myapp.Network.Async;
import com.contus.keerthi.myapp.Pojo.Gallery;
import com.contus.keerthi.myapp.DbEntry.dbHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Keerthivasan on 23/2/17.
 * Version 1.0
 * Class for Download Images from API and extract the data from JSON insert that to database, also get the Data from Database
 */

public class GetImages {
    /* ArrayList to hold Gallery Data as Objects*/
    ArrayList<Gallery> imageArrayList;
    /* Context variable to hold activity or class context*/
    Context context;
    /* Oject for dbHelper class to perform Database Operations */
    dbHelper dbHelper;

    /* Constructor */
    public GetImages(Context context)
    {
        imageArrayList = new ArrayList<Gallery>();
        this.context = context;
        dbHelper=new dbHelper(context,MyApp.DB_NAME,MyApp.DATABASE_VERSION);
    }

    /*Method for Get Images and its returns the Gallery Array List*/
    public ArrayList<Gallery> getImages()
    {
        /* Database Query for Get the record count from Sqlite Database*/
        String count_query = "SELECT "+MyApp.Gallery.COLUMN_IMAGE_TITLE+" FROM "+MyApp.Gallery.TABLE_NAME;
        /* Records Count stores into flag variable */
        int flag = dbHelper.getCount(count_query);
        /* Checking flag value less than O calls download Images Method*/

        if(flag<=0)
            downloadImages();
        /* Getting the Records of images from Database*/
        imageArrayList=dbHelper.getImages();
        /*returns arraylist of Gallery Object*/
        return imageArrayList;
    }

    /**
     * method for download images and Json parsing and inserting into database
     */
    public void downloadImages()
    {
        try {
            /* Getting the JSON String by calling Async Class and passing URL */
            String image_string = new Async().execute(MyApp.API.FLICKR_IMAGE_URL).get();
            /* Getting photos JSON Object from json string*/
            JSONObject jsonObject = new JSONObject(image_string).getJSONObject("photos");
            /* Getting jsonArray photo from json object*/
            JSONArray jsonArray = jsonObject.getJSONArray("photo");
            for(int i=0;i<=jsonArray.length();i++)
            {
                /* Extracting single row for Json Object*/
                JSONObject temp = jsonArray.getJSONObject(i);
                /* Generating Image URL from method by passing arguements*/
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
    }

    /**
     * to generate image url
     * @param farm_id
     * @param server_id
     * @param id
     * @param secret
     * @return
     */
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
