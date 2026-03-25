package com.example.movie_browser.presentation.detailsScreen

import com.example.movie_browser.domain.entity.Movie


sealed class DetailsScreenState {
    object Loading : DetailsScreenState()
    object Error : DetailsScreenState()
    data class Success(val movie: Movie) : DetailsScreenState()
}