package com.example.movie_browser.presentation.favouriteScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.movie_browser.presentation.MovieViewModel
import com.example.movie_browser.presentation.mainScreen.MovieCards

@Composable
fun FavouriteScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel,
    onMovieClick: (Int) -> Unit
) {
    val favouriteMovies by viewModel.favouriteMoviesList.collectAsState()

    if (favouriteMovies.isEmpty()) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "Ви ще не додали жодного фільму до обраного",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    } else {
        Column(modifier = modifier.fillMaxSize()) {
            Text(
                text = "Моє обране",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp, 24.dp, 16.dp, 8.dp)
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(favouriteMovies, key = { it.id }) { movie ->
                    MovieCards(
                        modifier = Modifier,
                        movie = movie,
                        onMovieClick = { onMovieClick(movie.id) },
                        onAddToFavouriteClick = {
                            viewModel.changeFavouriteStatus(
                                movie,
                                isFavourite = true
                            )
                        },
                        isFavourite = true
                    )
                }
            }
        }
    }
}