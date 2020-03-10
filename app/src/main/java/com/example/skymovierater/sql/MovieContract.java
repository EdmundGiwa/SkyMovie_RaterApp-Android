package com.example.skymovierater.sql;

import android.provider.BaseColumns;

public final class MovieContract {

    private MovieContract() {



    }

     public static class MovieEntry implements BaseColumns {
         // my User table name
         public static final String TABLE_USER = "user";
         public static final String COLUMN_USER_ID = "user_id";
         public static final String COLUMN_USER_NAME = "user_name";
         public static final String COLUMN_USER_EMAIL= "user_email";
         public static final String COLUMN_USER_PASSWORD= "user_password";


         public static final String MOVIE_TABLE_NAME= "movie";
         public static final String COLUMN_MOVIE_ID = "movie_id";
         public static final String COLUMN_MOVIE_TITLE = "movie_title";
         public static final String COLUMN_MOVIE_DESCRIPTION= "movie_description";
        

         // my rating table
         public static final String RATING_TABLE = "rate_table";
         public static final String COLUMN_RATING_ID = "rating_id";
         public static final String COLUMN_RATING_RATE = "rate";
         public static final String COLUMN_RATING_MOVIETITLE = "rating_title";
         public static final String COLUMN_RATING_REVIEW = "rating_review";
         public static final  String COLUMN_RATING_CATEGORY = "rating_category";



     }
}
