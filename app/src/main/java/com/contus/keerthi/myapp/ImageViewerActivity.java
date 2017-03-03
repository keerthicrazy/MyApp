package com.contus.keerthi.myapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ImageViewerActivity extends AppCompatActivity {

    ImageView imageView,BackButton;
    TextView TitleText;
    Toolbar toolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        toolbar = (Toolbar)findViewById(R.id.gallery_toolbar);
        setSupportActionBar(toolbar);
        BackButton = (ImageView)findViewById(R.id.galleryBackbtn);
        TitleText = (TextView)findViewById(R.id.galleryTitleText);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        TitleText.setText("Gallery");

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
}
