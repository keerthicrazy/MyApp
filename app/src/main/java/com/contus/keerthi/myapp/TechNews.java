package com.contus.keerthi.myapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contus.keerthi.myapp.POJO.News;
import com.contus.keerthi.myapp.Custom.GetNews;
import com.contus.keerthi.myapp.Custom.customNewsAdapter;

import java.util.ArrayList;

/**
 * Created by user on 27/2/17.
 */
public class TechNews extends Fragment {

    ArrayList<News> newsArrayList;
    RecyclerView recyclerView;
    com.contus.keerthi.myapp.Custom.customNewsAdapter customNewsAdapter;
    GetNews getNews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.news,container,false);
        newsArrayList = new ArrayList<News>();
        getNews = new GetNews(getActivity());
        recyclerView=(RecyclerView)view.findViewById(R.id.news_recycle_view);
        newsArrayList = getNews.getNews("tech");
        customNewsAdapter = new customNewsAdapter(newsArrayList,getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(customNewsAdapter);
        return view;
    }
}
