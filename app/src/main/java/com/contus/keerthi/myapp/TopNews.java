package com.contus.keerthi.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contus.keerthi.myapp.Pojo.News;
import com.contus.keerthi.myapp.Custom.GetNews;
import com.contus.keerthi.myapp.Custom.CustomNewsAdapter;

import java.util.ArrayList;

/**
 * Created by user on 27/2/17.
 */
public class TopNews extends Fragment {
    ArrayList<News> newsArrayList;
    RecyclerView recyclerView;
    CustomNewsAdapter CustomNewsAdapter;
    GetNews getNews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.news, container, false);
        newsArrayList = new ArrayList<News>();
        getNews = new GetNews(getActivity());
        recyclerView = (RecyclerView) view.findViewById(R.id.news_recycle_view);
        newsArrayList = getNews.getNews("top");
        CustomNewsAdapter = new CustomNewsAdapter(newsArrayList, getActivity(), adapterInterface);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(CustomNewsAdapter);
        return view;
    }

    CustomNewsAdapter.AdapterInterface adapterInterface = new com.contus.keerthi.myapp.Custom.CustomNewsAdapter.AdapterInterface() {
        @Override
        public void fetchData(View view,int position) {
            News news = newsArrayList.get(position);
            Intent intent = new Intent(getActivity(), NewsViewerActivity.class);
            intent.putExtra("imageUrl",news.getImage_url());
            intent.putExtra("newsTitle",news.getTitle());
            intent.putExtra("newsDes",news.getDes());
            intent.putExtra("newsURL",news.getUrl());
            intent.putExtra("newsSrc",news.getSource());
            startActivity(intent);
        }
    };
}
