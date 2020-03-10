package com.example.skymovierater.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.VisibleForTesting;

import com.example.skymovierater.models.User;
import com.example.skymovierater.sql.MovieContract.MovieEntry;

public class DatabaseHelper  extends SQLiteOpenHelper {

    // my db version
    private static final int DATABASE_VERSION = 1;
    // my db name
    private static final String DATABASE_NAME = "MovieRating.db";




    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MovieEntry.TABLE_USER;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // my table columns
        // creating user table
        String CREATE_USER_TABLE = " CREATE  TABLE " + MovieContract.MovieEntry.TABLE_USER + "("
                + MovieContract.MovieEntry.COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MovieContract.MovieEntry.COLUMN_USER_NAME + " TEXT,"
                + MovieContract.MovieEntry.COLUMN_USER_EMAIL + " TEXT,"
                + MovieContract.MovieEntry.COLUMN_USER_PASSWORD + " TEXT" + ") ";

        // creating rating table
        String CREATE_RATING = " CREATE  TABLE " + MovieEntry.RATING_TABLE + "("
                + MovieEntry.COLUMN_RATING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MovieEntry.COLUMN_RATING_RATE + " TEXT,"
                + MovieEntry.COLUMN_RATING_MOVIETITLE+ " TEXT, "
                + MovieEntry.COLUMN_RATING_REVIEW + " TEXT,"
                + MovieEntry.COLUMN_RATING_CATEGORY + " TEXT" + ") ";


        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_RATING);
//        db.execSQL(CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}