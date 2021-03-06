package com.contus.keerthi.myapp.Contract;

import android.provider.BaseColumns;

/**
 * Created by Keerthivasan on 22/2/17.
 * version 1.0
 * Class Created for Contract with Sqlite Database and also deeling with Constants in Application
 */

public class MyApp {

    /* Application Database Name */
    public static final String DB_NAME = "myapp.db";
    /* Application Database Version*/
    public static final int DATABASE_VERSION = 1;

    /* Class for News Table */
    public static class news implements BaseColumns {
        public static final String TABLE_NAME = "news";
        public static final String COLUMN_NAME_SOURCE = "source";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DES = "news_des";
        public static final String COLUMN_NAME_URL = "url";
        public static final String COLUMN_NAME_IMAGE_URL = "image_url";
        public static final String COLUMN_NAME_PUBLISHEDAT = "publishedAt";
        public static final String COLUMN_NAME_NEWS_TYPE = "news_type";
    }

    /*Class for Gallery Table */
    public static class Gallery implements BaseColumns {
        public static final String TABLE_NAME = "gallery";
        public static final String COLUMN_IMAGE_SRC = "image_url";
        public static final String COLUMN_IMAGE_TITLE = "image_title";
    }

    /* Class for external API's Like News API, FLICKR Image API */
    public static class API {
        public static final String NEWS_API_LATEST = "https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=ca4b1b240f684c8d9001bfd7d9cdd78e";
        public static final String NEWS_API_TECH = "https://newsapi.org/v1/articles?source=techcrunch&sortBy=top&apiKey=ca4b1b240f684c8d9001bfd7d9cdd78e";
        public static final String NEWS_API_TOP = "https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=ca4b1b240f684c8d9001bfd7d9cdd78e";

        public static final String FLICKR_IMAGE_URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=911f0332a1f208e7352591356af39c9c&per_page=500&user_id=52540720@N02&format=json&nojsoncallback=1";
    }

    /* Class for News Pager Names*/
    public static class PagerNames {
        public static final String NEWS_PAGER[] = {"TOP", "LATEST", "TECH"};
    }
}
