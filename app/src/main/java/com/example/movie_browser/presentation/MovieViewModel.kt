package com.example.movie_browser.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie_browser.domain.entity.Movie
import com.example.movie_browser.domain.usecases.ChangeFavouriteStateUseCase
import com.example.movie_browser.domain.usecases.GetDetailsMovieUseCase
import com.example.movie_browser.domain.usecases.GetFavouriteMoviesUseCase
import com.example.movie_browser.domain.usecases.GetPopularMoviesUseCase
import com.example.movie_browser.domain.usecases.ObserveIsFavouriteUseCase
import com.example.movie_browser.presentation.detailsScreen.DetailsScreenState
import com.example.movie_browser.presentation.mainScreen.MainScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetDetailsMovieUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val changeFavouriteStateUseCase: ChangeFavouriteStateUseCase,
    private val getFavouriteMoviesUseCase: GetFavouriteMoviesUseCase,
    private val observeIsFavouriteUseCase: ObserveIsFavouriteUseCase
) : ViewModel() {

    private val _favouriteMoviesId =
        MutableStateFlow<Set<Int>>(emptySet())
    val favouriteMoviesId: StateFlow<Set<Int>> = _favouriteMoviesId
    private val _mainScreenState: MutableStateFlow<MainScreenState> =
        MutableStateFlow(MainScreenState.Initial)
    val mainScreenState: StateFlow<MainScreenState> = _mainScreenState

    private val _detailsState: MutableStateFlow<DetailsScreenState> =
        MutableStateFlow(DetailsScreenState.Loading)
    val detailsState: StateFlow<DetailsScreenState> = _detailsState


    private var observeFavouriteJob: Job? = null

    init {
        loadPosts()
        observeFavourites()

    }

    private fun observeFavourites() {
        viewModelScope.launch {
            getFavouriteMoviesUseCase().collect { movies ->
                _favouriteMoviesId.value = movies.map { it.id }.toSet()
            }
        }
    }

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _detailsState.value = DetailsScreenState.Loading
            try {
                val movie = getMovieDetailsUseCase(movieId = movieId)
                observeFavouriteJob?.cancel()

                observeFavouriteJob = launch {
                    observeIsFavouriteUseCase(movieId).collect { isFav ->
                        _detailsState.value = DetailsScreenState.Success(movie, isFav)
                    }
                }
            } catch (e: Exception) {
                _detailsState.value = DetailsScreenState.Error
            }
        }
    }

    fun loadPosts() {
        viewModelScope.launch {
            _mainScreenState.value = MainScreenState.Loading
            try {
                val posts = getPopularMoviesUseCase()
                _mainScreenState.value = MainScreenState.Posts(posts)
            } catch (e: Exception) {
                _mainScreenState.value = MainScreenState.Error

            }
        }
    }

    fun changeFavouriteStatus(movie: Movie, isFavourite: Boolean) {
        viewModelScope.launch {
            if (isFavourite) {
                changeFavouriteStateUseCase.removeFromFavourite(movie.id)
            } else {
                changeFavouriteStateUseCase.addToFavourite(movie)
            }
        }
    }
}