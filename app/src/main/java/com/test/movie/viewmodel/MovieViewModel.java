package com.test.movie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.movie.model.MovieResponse;
import com.test.movie.networking.MovieRepository;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<MovieResponse> mutableLiveData;
    private MovieRepository movieRepository;

    public void init() {
        if (mutableLiveData != null)
            return;

        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<MovieResponse> getMovies(int page) {
        mutableLiveData = movieRepository.getMovies(page);

        return mutableLiveData;
    }

    public LiveData<MovieResponse> getMoviesRepository() {
        return mutableLiveData;
    }
}
