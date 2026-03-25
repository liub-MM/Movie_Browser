package com.example.movie_browser.presentation.mainScreen

import com.example.movie_browser.domain.entity.Movie

sealed class MainScreenState {

    object Initial : MainScreenState()
    object Error : MainScreenState()
    object Loading : MainScreenState()
    data class Posts(val list: List<Movie>) : MainScreenState()
}