package com.example.skymovierater.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.skymovierater.adapters.MovieItemClickListener;
import com.example.skymovierater.models.Movie;
import com.example.skymovierater.adapters.MovieAdapter;
import com.example.skymovierater.R;
import com.example.skymovierater.adapters.SliderPagerAdapter;
import com.example.skymovierater.models.slide;
import com.example.skymovierater.utils.DataSource;
import com.google.android.material.tabs.TabLayout;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class movie_activity extends AppCompatActivity  implements MovieItemClickListener {

    private List<slide> list_slides;
    private ViewPager sliderPager;
    private TabLayout indicator;
    private RecyclerView MoviesRv, MoviesRv_week;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_movie_activity);

        iniViews();
        iniSlider();
        iniPopular_Movies();
        iniWeek_Movies();

    }

    private void iniWeek_Movies() {
        MovieAdapter weekMovieAdapter = new MovieAdapter(this, DataSource.getWeekMovies(), this);
        MoviesRv_week.setAdapter(weekMovieAdapter);
        MoviesRv_week.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

    }

    // popular movies
    private void iniPopular_Movies() {
        // Recyclerview  setup
        // initialize Data
        // button thumbnails

        MovieAdapter movieAdapter = new MovieAdapter(this, DataSource.getPopularMovies(), this);
        MoviesRv.setAdapter(movieAdapter);
        MoviesRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    // initialized sliders
    private void iniSlider() {
        // List of my Slides
        // top movie slides
        list_slides = new ArrayList<>();
        list_slides.add(new slide(R.drawable.slide1_madmax, "Mad Max \nAction"));
        list_slides.add(new slide(R.drawable.slide2_lion, "Lion King \nFamily"));
        list_slides.add(new slide(R.drawable.slide3_venom, "Venom\nAction"));
        list_slides.add(new slide(R.drawable.slide4_shazam,"Shazam\nAction Comedy"));
        list_slides.add(new slide(R.drawable.slide6_endgame, "Avengers Endgame\nAction"));
        list_slides.add(new slide(R.drawable.slide7_aladdin , "Aladdin\nFantasy"));
        list_slides.add(new slide(R.drawable.slide8_darkpheonix , "Dark Pheonix\nAction"));
        list_slides.add(new slide(R.drawable.slide9_hobbshaw , "Hobbs and Shaw\nAction"));
        list_slides.add(new slide(R.drawable.slide10_it, "IT\nHorror"));
        //End of List
        SliderPagerAdapter adapter = new SliderPagerAdapter(this, list_slides);
        sliderPager.setAdapter(adapter);
        //setting up my slider
        // with Indicator
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SlideTimer(), 4000, 6000);
        indicator.setupWithViewPager(sliderPager, true);
    }

    // my initialized views
    private void iniViews() {
        sliderPager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        MoviesRv = findViewById(R.id.Rv_movies);
        MoviesRv_week = findViewById(R.id.Rv_week_movies);
    }


    // on mouse click
    @Override
    public void onMovieClick(Movie movie, ImageView movieImageView) {
        // sending movie information to detail activity
        //  creating a transition animation between the two activity
            Intent intent = new Intent(this, movieDetsActivity.class);

            // sending info to movie Details
            intent.putExtra("title",movie.getTitle());
            intent.putExtra("imgUrl", movie.getThumbnail());
            intent.putExtra("imgCover", movie.getCoverPhoto());
            intent.putExtra("description", movie.getDecsription());
//            startActivity(intent);

            // creating Animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(movie_activity.this, movieImageView
                ,"sharedName");

        startActivity(intent, options.toBundle());
        // test if the click works
        StyleableToast.makeText(this, "Item Clicked: "+movie.getTitle(), Toast.LENGTH_LONG, R.style.clickedToast).show();
//        styleableToast.makeText(this, "Item Clicked: "+movie.getTitle(), Toast.LENGTH_LONG).show();

    }


    // timer class
    class SlideTimer extends TimerTask{

        @Override
        public void run() {
            movie_activity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                        if(sliderPager.getCurrentItem() <list_slides.size()-1){
                                sliderPager.setCurrentItem(sliderPager.getCurrentItem()+1);
                        }
                        else sliderPager.setCurrentItem(0);

                }
            });

        }
    }
}
