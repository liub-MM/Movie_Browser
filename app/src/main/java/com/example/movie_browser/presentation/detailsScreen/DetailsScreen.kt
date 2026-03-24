package com.example.movie_browser.presentation.detailsScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.movie_browser.presentation.MovieViewModel
import com.example.movie_browser.utils.formatReleaseYear
import com.example.moviebrowser.data.entityDto.MovieDto

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieViewModel
) {
    val state by viewModel.detailsState.collectAsState()

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (val currentState = state) {
            is DetailsScreenState.Loading -> {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }

            is DetailsScreenState.Error -> {
                Text(
                    text = "Сталася помилка при завантаженні деталей.",
                    color = MaterialTheme.colorScheme.error
                )
            }

            is DetailsScreenState.Success -> {
                DetailsContent(
                    movie = currentState.movie,
                    onFavoriteClick = {},
                    isFavorite = false
                )
            }
        }
    }
}

@Composable
private fun DetailsContent(
    movie: MovieDto,
    onFavoriteClick: (MovieDto) -> Unit,
    isFavorite: Boolean
) {
    val backdropUrl = "https://image.tmdb.org/t/p/w780${movie.backdropPath}"
    val posterUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            ) {
                AsyncImage(
                    model = backdropUrl,
                    contentDescription = "Background for ${movie.title}",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    MaterialTheme.colorScheme.background
                                ),
                                startY = 300f
                            )
                        )
                )


                AsyncImage(
                    model = posterUrl,
                    contentDescription = "Poster for ${movie.title}",
                    modifier = Modifier
                        .width(130.dp)
                        .height(200.dp)
                        .align(Alignment.BottomCenter)
                        .offset(y = (-20).dp),
                    contentScale = ContentScale.Crop
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 36.sp,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = formatReleaseYear(movie.releaseDate),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedButton(
                    onClick = { onFavoriteClick(movie) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (isFavorite) "З обраного" else "В обране",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "Опис",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 26.sp,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
            }
        }
    }
}