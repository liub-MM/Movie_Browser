package com.example.movie_browser.domain.usecases

import com.example.movie_browser.domain.Repository
import com.example.movie_browser.domain.entity.Movie
import javax.inject.Inject

class ChangeFavouriteStateUseCase @Inject constructor(private val repository: Repository) {

    suspend fun addToFavourite(movie: Movie) {
        repository.addToFavourite(movie)
    }

    suspend fun removeFromFavourite(movieID: Int) {
        repository.removeFromFavourite(movieID)
    }

}