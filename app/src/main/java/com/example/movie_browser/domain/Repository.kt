package com.example.movie_browser.domain

import com.example.movie_browser.domain.entity.Movie
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun addToFavourite(movie: Movie)

    suspend fun removeFromFavourite(movieId: Int)

    val favouriteMovies: Flow<List<Movie>>

    fun observeIsFavourite(movieId: Int): Flow<Boolean>


    suspend fun getPopularMovies(): List<Movie>

    suspend fun getMovieDetails(movieId: Int): Movie
}