package com.contus.keerthi.myapp.Custom;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.contus.keerthi.myapp.ImageViewerActivity;
import com.contus.keerthi.myapp.POJO.Gallery;
import com.contus.keerthi.myapp.R;

import java.util.ArrayList;

/**
 * Created by Keerthivasan on 23/2/17.
 * version 1.0
 * Custom Recycle View Adapter for Gallery to initialize the view and load the data
 */

public class CustomGalleryAdapter extends RecyclerView.Adapter<CustomGalleryAdapter.MyViewHolder> {

    /*Array List for Holding Gallery Data*/
    ArrayList<Gallery> imageArrayList;
    /* Context variable to hold activity or class context*/
    Context context;

    /* Arguement Constructor that receives arraylist and context*/
    public CustomGalleryAdapter(ArrayList<Gallery> imageArrayList, Context context) {
        this.imageArrayList = imageArrayList;
        this.context = context;
    }

    /* on create view holder to inflate the xml layout*/
    @Override
    public CustomGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
        return new CustomGalleryAdapter.MyViewHolder(itemview);
    }

    /* on bind view holder to load the data into xml*/
    @Override
    public void onBindViewHolder(CustomGalleryAdapter.MyViewHolder holder, int position) {

        /* Get the Single Image Record From Array List*/
        final Gallery image = imageArrayList.get(position);

        /* Loading the image in ImageView using GLide Library*/
        Glide
                .with(context)
                .load(image.getImageUrl())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imageView);

        /*set the value in image title*/
        holder.textView.setText(image.getImageTitle());

        /*click event of gallery image*/
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick(image.getImageUrl());
            }
        });

        /* Click Event for Image title */
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick(image.getImageUrl());
            }
        });
    }

    /*method for click event from image and description*/
    public void onclick(String url) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra("imgUrl", url);
        context.startActivity(intent);
    }

    /* Return the ArrayList Size*/
    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    /* Innerclass for View Holder to initialize the view elements from layout xml*/
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        /* Constructor that initialize the view elements*/
        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.gallery_images);
            textView = (TextView) view.findViewById(R.id.gal_image_des);
        }
    }
}

