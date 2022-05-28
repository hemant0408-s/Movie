package com.test.movie;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.test.movie.databinding.MovieDetailsBinding;
import com.test.movie.model.Movie;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    private MovieDetailsBinding movieDetailsBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movieDetailsBinding = DataBindingUtil.setContentView(this, R.layout.movie_details);

        Movie movie = (Movie) getIntent().getExtras().getSerializable(EXTRA_MOVIE);

        ViewDataBinding viewDataBinding = DataBindingUtil.bind(movieDetailsBinding.getRoot());
        viewDataBinding.setVariable(BR.entry, movie);
        viewDataBinding.executePendingBindings();
    }
}
