package com.contus.keerthi.myapp.Network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class getMovies extends AsyncTask<String, String, String> {


    Context context;


    public getMovies(Context context)
    {
        this.context=context;
    }

    @Override
    protected void onPreExecute() {

        Log.i("", "onPreExecute: pre-execute ");
        Toast.makeText(context,"loading . . .",Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground(String... urls) {

        try {
            URL url=new URL(urls[0]);
            StringBuilder mStringBuilder = new StringBuilder();
            URLConnection con=url.openConnection();
            con.setDoInput(true);
            BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line;
            while ((line=reader.readLine())!=null)
            {
                mStringBuilder.append(line);
            }
            Log.i("", "doInBackground: result "+ mStringBuilder.toString());
            return mStringBuilder.toString();

        }
        catch (Exception e) {
            Log.e("", "doInBackground: "+e.getMessage().toString() );
            return null;
        }
    }



}
