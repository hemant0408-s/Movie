package com.test.movie.networking;

import com.test.movie.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("movie/popular")
    Call<MovieResponse> getMoviesList(@Query("page") int page);
}
