package com.contus.keerthi.myapp.POJO;

/**
 * Created by user on 23/2/17.
 */

public class Gallery {

    private String ImageUrl;
    private String AuthorName;

    public Gallery(){
    }

    public Gallery(String imageUrl, String authorName) {
        ImageUrl = imageUrl;
        AuthorName = authorName;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }
}
