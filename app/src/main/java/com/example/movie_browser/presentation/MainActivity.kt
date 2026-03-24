package com.example.movie_browser.presentation

import MovieBrowserTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    private val viewModel: MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieBrowserTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding), viewModel)
                }
            }
        }
    }

}

@Composable
private fun MainScreen(
    modifier: Modifier,
    viewModel: MovieViewModel
) {
    val mainScreenState by viewModel.mainScreenState.collectAsState()

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
                        MovieCard(modifier, movie)
                    }
                }
            }
        }
    }
}