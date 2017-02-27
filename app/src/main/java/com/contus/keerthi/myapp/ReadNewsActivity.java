package com.contus.keerthi.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.contus.keerthi.myapp.POJO.News;

public class ReadNewsActivity extends AppCompatActivity {

    private ImageView iv_read_news_image;
    private TextView tv_read_news_title,tv_read_news_des,tv_read_news_story;
    private Button b_news_share;

    String imageUrl,newsTitle,newsDes,newsURL,newsSrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_read_news);


        try {
                iv_read_news_image = (ImageView)findViewById(R.id.readnewsimage);
                tv_read_news_title = (TextView)findViewById(R.id.read_news_title);
                tv_read_news_des = (TextView)findViewById(R.id.read_news_des);
                tv_read_news_story = (TextView)findViewById(R.id.read_news_full_story);
                b_news_share = (Button)findViewById(R.id.read_share);

                Intent intent = getIntent();

                if(intent != null){
                    imageUrl = intent.getStringExtra("imageUrl");
                    newsTitle = intent.getStringExtra("newsTitle");
                    newsDes = intent.getStringExtra("newsDes");
                    newsURL =intent.getStringExtra("newsURL");
                    newsSrc = intent.getStringExtra("newsSrc");

                }else{
                    onBackPressed();
                }

                Glide.with(this)
                        .load(imageUrl)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(iv_read_news_image);


                if(newsDes == null)
                    newsDes ="News Description Not Available";
                tv_read_news_title.setText(newsTitle);
                tv_read_news_des.setText(newsDes);
                tv_read_news_story.setText("Full Story "+(Html.fromHtml("<a href="+newsURL+"> "+newsSrc+" </a>")));
                tv_read_news_story.setMovementMethod(LinkMovementMethod.getInstance());

                b_news_share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"news Share Button",Toast.LENGTH_LONG).show();
                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
