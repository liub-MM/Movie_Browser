package com.example.movie_browser.presentation.detailsScreen

import com.example.moviebrowser.data.entityDto.MovieDto


sealed class DetailsScreenState {
    object Loading : DetailsScreenState()
    object Error : DetailsScreenState()
    data class Success(val movie: MovieDto) : DetailsScreenState()
}