package com.example.moviebrowser.data

import com.example.moviebrowser.data.entityDto.MovieDto
import com.example.moviebrowser.data.entityDto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "uk-UA",
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @retrofit2.http.Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "uk-UA"
    ): MovieDto
}