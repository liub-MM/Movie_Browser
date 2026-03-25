package com.example.movie_browser.domain.usecases

import com.example.movie_browser.domain.Repository
import com.example.movie_browser.domain.entity.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouriteMoviesUseCase @Inject constructor(private val repository: Repository) {
     operator fun invoke(): Flow<List<Movie>> = repository.favouriteMovies
}