package com.contus.keerthi.myapp.POJO;

/**
 * Created by user on 8/2/17.
 */

public class MovieRowItem {


        private String title;
        private String desc;

    public MovieRowItem(String title, String desc) {

        this.title = title;
        this.desc = desc;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return title + "\n" + desc;
    }
}