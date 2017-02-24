package com.contus.keerthi.myapp.POJO;

import android.widget.ImageView;

import java.sql.Date;


/**
 * Created by user on 22/2/17.
 */

public class News {

    private String source;
    private String author;
    private String title;
    private String des;
    private String url;
    private String image_url;
    private String date;


    public News()
    {
    }

    public News(String source, String author, String title, String des, String url, String image_url, String date) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.des = des;
        this.url = url;
        this.image_url = image_url;
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
