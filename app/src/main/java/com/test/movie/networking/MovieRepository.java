package com.test.movie.networking;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.test.movie.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository movieRepository;
    private API api;

    public static MovieRepository getInstance(){
        if (movieRepository == null)
            movieRepository = new MovieRepository();

        return movieRepository;
    }

    public MovieRepository() {
        api = RetrofitService.createService(API.class);
    }

    public MutableLiveData<MovieResponse> getMovies(int page) {
        MutableLiveData<MovieResponse> responseMutableLiveData = new MutableLiveData<>();
        api.getMoviesList(page).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call,
                                   @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful())
                    responseMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                responseMutableLiveData.setValue(null);
            }
        });

        return responseMutableLiveData;
    }
}
