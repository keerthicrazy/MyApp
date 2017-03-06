package com.contus.keerthi.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.contus.keerthi.myapp.Pojo.News;
import com.contus.keerthi.myapp.Custom.GetNews;
import com.contus.keerthi.myapp.Custom.CustomNewsAdapter;

import java.util.ArrayList;
import com.contus.keerthi.myapp.Custom.CustomNewsAdapter.AdapterInterface;

/**
 * Created by Keerthivasan on 20/2/17.
 */

public class FetchNews extends Fragment implements SearchView.OnQueryTextListener {

    ArrayList<News> newsArrayList;
    RecyclerView recyclerView;
    CustomNewsAdapter customNewsAdapter;
    GetNews getNews;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news,container,false);
        String newsType = getArguments().getString("newsType","top");
        newsArrayList = new ArrayList<News>();
        getNews = new GetNews(getActivity());
        recyclerView=(RecyclerView)view.findViewById(R.id.news_recycle_view);
        newsArrayList = getNews.getNews(newsType);
        customNewsAdapter = new CustomNewsAdapter(newsArrayList,getActivity(), adapterInterface);
        customNewsAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customNewsAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    CustomNewsAdapter.AdapterInterface adapterInterface = new AdapterInterface() {
        @Override
        public void fetchData(View view,int position) {
            News news = newsArrayList.get(position);
            Intent intent = new Intent(view.getContext(), NewsViewerActivity.class);
            intent.putExtra("imageUrl",news.getImage_url());
            intent.putExtra("newsTitle",news.getTitle());
            intent.putExtra("newsDes",news.getDes());
            intent.putExtra("newsURL",news.getUrl());
            intent.putExtra("newsSrc",news.getSource());
            view.getContext().startActivity(intent);
        }
    };

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu,menu);
        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        return true;
                    }
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        return true;
                    }
                });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                customNewsAdapter=new CustomNewsAdapter(newsArrayList,getActivity(),adapterInterface);
                customNewsAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(customNewsAdapter);
                return true;
            }
        });

    }



    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i("news", "onQueryTextChange: "+newText);
        if(newText.length()>2){
            final ArrayList<News> filterList = filter(newsArrayList,newText);
            customNewsAdapter=new CustomNewsAdapter(filterList,getActivity(),adapterInterface);
            customNewsAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(customNewsAdapter);
        }
        return true;
    }

    private ArrayList<News> filter(ArrayList<News> models, String query) {
        query = query.toLowerCase();
        final ArrayList<News> filteredModelList = new ArrayList<>();
        for (News model : models) {
            final String text = model.getTitle().toLowerCase();
            if (text.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }
}
