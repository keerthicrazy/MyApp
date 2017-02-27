package com.contus.keerthi.myapp.Contract;

import android.provider.BaseColumns;

/**
 * Created by user on 22/2/17.
 */

public class MyApp {

    public static final String DB_NAME="myapp.db";
    public static final int DATABASE_VERSION = 1;

    public static class news implements BaseColumns{
        public static final String TABLE_NAME = "news";
        public static final String COLUMN_NAME_SOURCE = "source";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DES ="news_des";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_IMAGE_URL = "image_url";
        public static final String COLUMN_NAME_PUBLISHEDAT = "publishedAt";
    }

    public static class API {
        public static final String NEWS_API = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=ca4b1b240f684c8d9001bfd7d9cdd78e";
        public static final String FLICKR_IMAGE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=911f0332a1f208e7352591356af39c9c&per_page=500&user_id=52540720@N02&format=json&nojsoncallback=1";
    }

    public static class PagerNames {
        public static final String NEWS_PAGER[] ={"Latest","POPULAR"};
    }
}
