package com.contus.keerthi.myapp.POJO;

/**Pojo class for Gallery
 * Created by Keerthivasan on 23/2/17.
 */

public class Gallery {

    private String ImageUrl;
    private String ImageTitle;

    public Gallery(){
    }

    public Gallery(String imageUrl, String imageTitle) {
        ImageUrl = imageUrl;
        ImageTitle = imageTitle;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getImageTitle() {
        return ImageTitle;
    }

    public void setImageTitle(String imageTitle) {
        ImageTitle = imageTitle;
    }
}
