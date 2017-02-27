package com.contus.keerthi.myapp.custom;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.contus.keerthi.myapp.POJO.Gallery;
import com.contus.keerthi.myapp.R;

import java.util.ArrayList;

/**
 * Created by user on 23/2/17.
 */

public class CustomGalleryAdapter extends RecyclerView.Adapter<CustomGalleryAdapter.MyViewHolder> {

        ArrayList<Gallery> imageArrayList;
        Context context;

        public class MyViewHolder extends RecyclerView.ViewHolder{

            public ImageView imageView;
            public TextView textView;

            public MyViewHolder(View view) {
                super(view);
                imageView = (ImageView)view.findViewById(R.id.gallery_images);
                textView = (TextView)view.findViewById(R.id.gal_image_des);
            }
        }

        public CustomGalleryAdapter(ArrayList<Gallery> imageArrayList, Context context) {
            this.imageArrayList = imageArrayList;
            this.context = context;
        }

    @Override
    public CustomGalleryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item,parent,false);
        return new CustomGalleryAdapter.MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(CustomGalleryAdapter.MyViewHolder holder, int position) {
        Gallery image = imageArrayList.get(position);
        Glide
                .with(context)
                .load(image.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.imageView);
        holder.textView.setText(image.getImageTitle());
    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }
}

