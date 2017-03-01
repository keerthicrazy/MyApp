package com.contus.keerthi.myapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ImageViewerActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_image_viewer);
        this.setTitle("Image Viewer");
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDefaultDisplayHomeAsUpEnabled(true);

        }
        Intent intent = getIntent();

        String imgUrl = "";

        if(intent != null){
            imgUrl = intent.getStringExtra("imgUrl");
        }else{
            onBackPressed();
        }

        imageView = (ImageView)findViewById(R.id.imageViewer);

        Glide
                .with(this)
                .load(imgUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
