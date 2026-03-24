package com.example.movie_browser.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviebrowser.data.ApiFactory
import com.example.moviebrowser.data.ApiService
import com.example.moviebrowser.data.entityDto.MovieDto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieViewModel() : ViewModel() {
    private val apiService = ApiFactory.api

    private val _mainScreenState: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Initial)
    val mainScreenState: StateFlow<MainScreenState> = _mainScreenState

    init {
        loadPosts()
    }

    fun loadPosts() {
        viewModelScope.launch {
            _mainScreenState.value = MainScreenState.Loading
            try {
                val posts = apiService.getPopularMovies().results
                _mainScreenState.value = MainScreenState.Posts(posts)
            } catch (e: Exception) {
                _mainScreenState.value = MainScreenState.Error

            }
        }
    }

}