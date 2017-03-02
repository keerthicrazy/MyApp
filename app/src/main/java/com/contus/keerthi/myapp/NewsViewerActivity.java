package com.contus.keerthi.myapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class NewsViewerActivity extends AppCompatActivity {

    private ImageView iv_read_news_image;
    private TextView tv_read_news_title,tv_read_news_des,tv_read_news_story;
    private Button b_news_share;

    String imageUrl,newsTitle,newsDes,newsURL,newsSrc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_viewer);
        try {
            ActionBar actionBar = getSupportActionBar();
            if(actionBar != null){
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDefaultDisplayHomeAsUpEnabled(true);
            }

            this.setTitle("News");
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
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .into(iv_read_news_image);


            if(newsDes == null)
                newsDes ="News Description Not Available";
            tv_read_news_title.setText(newsTitle);
            tv_read_news_des.setText(newsDes);
            tv_read_news_story.setClickable(true);
            tv_read_news_story.setMovementMethod(LinkMovementMethod.getInstance());

            SpannableStringBuilder builder = new SpannableStringBuilder();

            SpannableString str1= new SpannableString("Full Story ");
            str1.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, str1.length(), 0);
            builder.append(str1);

            SpannableString str2= new SpannableString(newsSrc);
            str2.setSpan(new ForegroundColorSpan(Color.BLUE), 0, str2.length(), 0);
            builder.append(str2);

            tv_read_news_story.setText( builder, TextView.BufferType.SPANNABLE);

            tv_read_news_story.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(newsURL));
                    startActivity(i);
                }
            });

            b_news_share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ShareCompat.IntentBuilder
                            .from(NewsViewerActivity.this)
                            .setType("text/plain")
                            .setText("MyApp News")
                            .setText(newsTitle)
                            .setText(newsURL)
                            .setChooserTitle("Open using")
                            .startChooser();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
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
