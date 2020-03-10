package com.example.skymovierater.adapters;

import android.widget.ImageView;

import com.example.skymovierater.models.Movie;

public interface MovieItemClickListener {

    // making the imageview to make the shared animation between the two activity
    void onMovieClick(Movie movie, ImageView movieImageView);
}
