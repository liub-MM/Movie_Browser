package com.example.movie_browser.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_browser.data.RepositoryImpl
import com.example.movie_browser.presentation.detailsScreen.DetailsScreenState
import com.example.movie_browser.presentation.mainScreen.MainScreenState
import com.example.movie_browser.data.network.ApiFactory
import com.example.movie_browser.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _mainScreenState: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Initial)
    val mainScreenState: StateFlow<MainScreenState> = _mainScreenState

    private val _detailsState: MutableStateFlow<DetailsScreenState> =
        MutableStateFlow(DetailsScreenState.Loading)
    val detailsState: StateFlow<DetailsScreenState> = _detailsState

    init {
        loadPosts()
    }

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _detailsState.value = DetailsScreenState.Loading
            try {
                val movie = repository.getMovieDetails(movieId = movieId)
                _detailsState.value = DetailsScreenState.Success(movie)
            } catch (e: Exception) {
                _detailsState.value = DetailsScreenState.Error
            }
        }
    }

    fun loadPosts() {
        viewModelScope.launch {
            _mainScreenState.value = MainScreenState.Loading
            try {
                val posts = repository.getPopularMovies()
                _mainScreenState.value = MainScreenState.Posts(posts)
            } catch (e: Exception) {
                _mainScreenState.value = MainScreenState.Error

            }
        }
    }

}