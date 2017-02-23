package com.contus.keerthi.myapp.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.contus.keerthi.myapp.POJO.News;
import com.contus.keerthi.myapp.R;

import java.util.ArrayList;

/**
 * Created by user on 22/2/17.
 */

public class customNewsAdapter extends RecyclerView.Adapter<customNewsAdapter.MyViewHolder> {

    ArrayList<News> newsArrayList;
    Context context;

    private final static String TAG = "NewsAdapter";

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView iv_newsImage;
        public TextView tv_newsTitle,tv_newsAuth,tv_newsSrc;


        public MyViewHolder(View view) {
            super(view);
            iv_newsImage = (ImageView) view.findViewById(R.id.news_image);
            tv_newsTitle = (TextView)view.findViewById(R.id.news_title);
            tv_newsAuth = (TextView)view.findViewById(R.id.news_author);
            tv_newsSrc = (TextView)view.findViewById(R.id.news_src);
        }
    }

    public customNewsAdapter(ArrayList<News> newsArrayList,Context context) {
        this.newsArrayList = newsArrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News news = newsArrayList.get(position);

Glide.with(context).load(news.getImage_url()).diskCacheStrategy(DiskCacheStrategy.RESULT).into(holder.iv_newsImage);
        holder.tv_newsTitle.setText(news.getTitle());
        holder.tv_newsAuth.setText("by "+news.getAuthor());
        holder.tv_newsSrc.setText("soucre : "+news.getSource());
        Log.i(TAG, "onBindViewHolder: "+news.getDate());
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }
}
