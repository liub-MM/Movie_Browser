package com.example.moviebrowser.data

import com.example.moviebrowser.data.entityDto.MovieDto
import com.example.moviebrowser.data.entityDto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {


    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = KEY,
        @Query("language") language: String = "uk-UA",
    ): MovieResponse

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @retrofit2.http.Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = KEY,
        @Query("language") language: String = "uk-UA"
    ): MovieDto

    companion object {
        private const val KEY = "1aad0b0a4b27bdecb0814ac3ca2c09b1"
    }
}