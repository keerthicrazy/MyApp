package com.contus.keerthi.myapp.Custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
 * Created by Keerthivasan on 22/2/17.
 * Version 1.0
 * Custom Recycle View Adapter Class News to initialize the view and load the data
 */

public class CustomNewsAdapter extends RecyclerView.Adapter<CustomNewsAdapter.MyViewHolder> {

    /* TAG Variable to print the TAG in console */
    private final static String TAG = "NewsAdapter";
    /* Array List for Holding News Data*/
    ArrayList<News> newsArrayList;
    /* Context variable to hold activity or class context*/
    Context context;
    AdapterInterface adapterInterface;
    View view;


    public void setAdapterInterface(AdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
    }

    public interface AdapterInterface
    {
        public void fetchData(View view,int position);

    }



    /* Arguement Constructor that receives arraylist and context*/
    public CustomNewsAdapter(ArrayList<News> newsArrayList, Context context, AdapterInterface adapterInterface) {
        this.adapterInterface = adapterInterface;
        this.newsArrayList = newsArrayList;
        this.context = context;

    }

    /* on create view holder to inflate the xml layout*/
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item,parent,false);
        view = itemview;
        return new MyViewHolder(itemview);
    }

    /* on bind view holder to load the data into xml*/
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        /* Get the Single Image Record From Array List*/
        final News news = newsArrayList.get(position);
        final int p = position;

        /* Loading the image in ImageView using GLide Library*/
        Glide.with(context).load(news.getImage_url()).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.iv_newsImage);
        /*set the value in textview */
        holder.tv_newsTitle.setText(news.getTitle());
        holder.tv_newsAuth.setText("by "+news.getAuthor());
        holder.tv_newsSrc.setText("soucre : "+news.getSource());

    }


    /* Return the ArrayList Size*/
    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    /* Innerclass for View Holder to initialize the view elements from layout xml*/
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        /* Declaring View Elements*/
        public ImageView iv_newsImage;
        public TextView tv_newsTitle,tv_newsAuth,tv_newsSrc;
        public RelativeLayout relativeLayout;

        /* Constructor that initialize the view elements*/
        public MyViewHolder(View view) {
            super(view);
            iv_newsImage = (ImageView) view.findViewById(R.id.news_image);
            tv_newsTitle = (TextView)view.findViewById(R.id.news_title);
            tv_newsAuth = (TextView)view.findViewById(R.id.news_author);
            tv_newsSrc = (TextView)view.findViewById(R.id.news_src);
            relativeLayout=(RelativeLayout)view.findViewById(R.id.placeNameHolder);
            relativeLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            adapterInterface.fetchData(v,getPosition());

        }
    }


}
