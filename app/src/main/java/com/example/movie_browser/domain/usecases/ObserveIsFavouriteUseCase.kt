package com.example.movie_browser.domain.usecases

import com.example.movie_browser.domain.Repository
import javax.inject.Inject

class ObserveIsFavouriteUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(movieID: Int) = repository.observeIsFavourite(movieID)

}