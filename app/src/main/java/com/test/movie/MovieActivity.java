package com.test.movie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.test.movie.adapters.ItemClickListener;
import com.test.movie.adapters.MovieAdapter;
import com.test.movie.databinding.ActivityMainBinding;
import com.test.movie.model.Movie;
import com.test.movie.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, ItemClickListener {

    @BindView(R.id.restaurant_recycler_view)
    protected RecyclerView movieRecyclerView;
    @BindView(R.id.scroll_view)
    protected NestedScrollView scrollView;
    @BindView(R.id.progress_bar)
    protected ProgressBar progressBar;
    @BindView(R.id.swipe_refresh)
    protected SwipeRefreshLayout swipeRefreshLayout;

    private ActivityMainBinding activityMainBinding;

    int page = 1;

    private ArrayList<Movie> movies = new ArrayList<>();
    private MovieAdapter movieAdapter;
    private MovieViewModel movieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        ButterKnife.bind(this);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.init();

        getData(page);

        setupRecyclerView();

        swipeRefreshLayout.setOnRefreshListener(this);

        scrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                progressBar.setVisibility(View.VISIBLE);

                page++;

                getData(page);
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getData(1);
    }

    private void setupRecyclerView() {
        if (movieAdapter == null) {
            movieAdapter = new MovieAdapter(movies, this);

            movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            movieRecyclerView.setItemAnimator(new DefaultItemAnimator());
            movieRecyclerView.setNestedScrollingEnabled(true);

            movieRecyclerView.setAdapter(movieAdapter);
        } else
            movieAdapter.notifyDataSetChanged();
    }

    private void getData(int page) {
        movieViewModel.getMovies(page).observe(this, movieResponse -> {

            if (movieResponse != null && movieResponse.getMovies() != null && !movieResponse.getMovies().isEmpty()) {
                List<Movie> movies1 = movieResponse.getMovies();

                movies.clear();
                movies.addAll(movies1);

                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);

                activityMainBinding.setVariable(BR.showEmptyMessage, false);

                movieAdapter.notifyDataSetChanged();
            } else {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);

                activityMainBinding.setVariable(BR.showEmptyMessage, true);
            }
        });
    }

    @Override
    public void onItemClick(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.EXTRA_MOVIE, movie);
        startActivity(intent);
    }
}
