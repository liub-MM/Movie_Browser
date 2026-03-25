package com.example.movie_browser.domain.usecases

import com.example.movie_browser.domain.Repository
import com.example.movie_browser.domain.entity.Movie
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() :List<Movie> = repository.getPopularMovies()
}