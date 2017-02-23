package com.contus.keerthi.myapp.custom;

import com.contus.keerthi.myapp.Contract.MyApp;
import com.contus.keerthi.myapp.Network.Async;
import com.contus.keerthi.myapp.POJO.Gallery;

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

    public GetImages()
    {
        imageArrayList = new ArrayList<Gallery>();
    }

    public ArrayList<Gallery> downloadImages()
    {
        try {
            String image_string = new Async().execute(MyApp.API.IMAGE_URL).get();
            JSONArray jsonArray = new JSONArray(image_string);
            for(int i=0;i<=jsonArray.length();i++)
            {
                JSONObject temp = jsonArray.getJSONObject(i);
                Gallery gallery = new Gallery(temp.getString("post_url"),temp.getString("author"));
                imageArrayList.add(gallery);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
        }
        return  imageArrayList;
    }
}
