package com.example.movie_browser.presentation.mainScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.movie_browser.presentation.MovieViewModel

@Composable
fun MainScreen(
    modifier: Modifier,
    viewModel: MovieViewModel,
    onMovieClick: (Int) -> Unit
) {
    val mainScreenState by viewModel.mainScreenState.collectAsState()
    val favouriteIds by viewModel.favouriteMoviesId.collectAsState()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        when (val currentState = mainScreenState) {
            is MainScreenState.Initial,
            is MainScreenState.Loading -> {
                CircularProgressIndicator()
            }

            is MainScreenState.Error -> {
                Text(text = "Сталася помилка при завантаженні фільмів.")
            }

            is MainScreenState.Posts -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(currentState.list) { movie ->
                        val isFav = favouriteIds.contains(movie.id)
                        MovieCards(
                            modifier,
                            movie,
                            onMovieClick = { onMovieClick(movie.id) },
                            onAddToFavouriteClick = {
                                viewModel.changeFavouriteStatus(
                                    movie,
                                    isFav
                                )
                            },
                            isFavourite = isFav
                        )
                    }
                }
            }
        }
    }
}