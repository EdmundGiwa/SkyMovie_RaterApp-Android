package com.example.skymovierater.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.skymovierater.R;
import com.example.skymovierater.sql.DatabaseHelper;
import com.example.skymovierater.sql.MovieContract;
import com.example.skymovierater.sql.MovieContract.MovieEntry;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class movieDetsActivity extends AppCompatActivity {

    private ImageView MovieThumbnailImg, MovieCoverImg;
    private TextView tv_title, tv_description;
    private EditText review_text;
    private com.hsalf.smilerating.SmileRating ratesbar;

    private DatabaseHelper databaseHelper;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_dets);

        // iniViews
        iniViews();

        databaseHelper = new DatabaseHelper(this);
    }

    private boolean check_input(float rating, String review, String title) {
        if (rating == 0 && review.equals("")) {
            StyleableToast.makeText(movieDetsActivity.this, "All the fields are Empty", Toast.LENGTH_SHORT, R.style.emptyToast).show();
            return false;
        } else if (rating == 0 || review.equals("")) {
            StyleableToast.makeText(movieDetsActivity.this, "Please rate and review :)", Toast.LENGTH_SHORT, R.style.rate_reviewToast).show();
            return false;
        } else {
            return true;
        }
    }

    // fetch or get Data
    void iniViews() {
        String movieTitle = getIntent().getExtras().getString("title");
        int imageResourceId = getIntent().getExtras().getInt("imgUrl");
        int imageCover = getIntent().getExtras().getInt("imgCover");
        String movieDescription = getIntent().getExtras().getString("description");

        MovieThumbnailImg = findViewById(R.id.detail_movie_img);
        Glide.with(this).load(imageResourceId).into(MovieThumbnailImg);
        MovieThumbnailImg.setImageResource(imageResourceId);
        MovieCoverImg = findViewById(R.id.detail_movie_cover);
        Glide.with(this).load(imageCover).into(MovieCoverImg);
        tv_title = findViewById(R.id.detail_movie_title);
        tv_title.setText(movieTitle);
        tv_description = findViewById(R.id.detail_movie_description);
        tv_description.setText(movieDescription);
        // setup Animation
        MovieCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));
    }


    public void submit_review(View view) {
        ratesbar = findViewById(R.id.ratingBar);
        float rate = ratesbar.getRating();
        review_text = findViewById(R.id.review_txt);
        tv_title = findViewById(R.id.detail_movie_title);

        String review_field = review_text.getText().toString().trim();
        String title_rating = tv_title.getText().toString().trim();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        if (check_input(rate, review_field, title_rating) == true) {
            ContentValues content = new ContentValues();
            content.put(MovieEntry.COLUMN_RATING_RATE, rate);
            content.put(MovieEntry.COLUMN_RATING_REVIEW, review_field);

            long insert = db.insert(MovieContract.MovieEntry.RATING_TABLE, null, content);

            if (insert == -1) {
                Toast.makeText(movieDetsActivity.this, "Not Connected", Toast.LENGTH_SHORT).show();

            } else if (insert >= 0) {
                StyleableToast.makeText(movieDetsActivity.this, "Thank You for Your Review", Toast.LENGTH_SHORT,R.style.thanksToast).show();
            }

        } else if (check_input(rate, review_field, title_rating) == false) {

        }
    }

}
