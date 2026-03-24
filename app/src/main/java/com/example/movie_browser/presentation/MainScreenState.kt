package com.example.movie_browser.presentation

import com.example.moviebrowser.data.entityDto.MovieDto

sealed class MainScreenState {

    object Initial : MainScreenState()
    object Error : MainScreenState()
    object Loading : MainScreenState()
    data class Posts(val list : List<MovieDto>): MainScreenState()
}