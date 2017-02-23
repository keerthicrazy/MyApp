package com.contus.keerthi.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.contus.keerthi.myapp.Network.getMovies;
import com.contus.keerthi.myapp.POJO.MovieRowItem;
import com.contus.keerthi.myapp.custom.CustomListMovieViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by user on 21/2/17.
 */

public class MovieFragement extends Fragment {

    ArrayList<MovieRowItem> rowItems;
    ListView list_v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.movie_fragment, container, false);
        rowItems = new ArrayList();

        try {
            String s = new getMovies(getActivity()).execute("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=9d1e1af7f7c076c3d57db6928a054408").get();
            JSONObject json = new JSONObject(s);
            JSONArray jsonArray = json.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject j = jsonArray.getJSONObject(i);
                MovieRowItem item = new MovieRowItem(j.get("original_title").toString(), j.get("overview").toString());
                rowItems.add(item);
            }

            list_v = (ListView) view.findViewById(R.id.List_view);
            CustomListMovieViewAdapter adapter = new CustomListMovieViewAdapter(getActivity(),
                    R.layout.movie_list_item, rowItems);
            list_v.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}
