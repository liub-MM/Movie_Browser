package com.example.movie_browser.presentation

import MovieBrowserTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.movie_browser.navigation.MovieNavGraph
import com.example.movie_browser.navigation.rememberNavigationState
import com.example.movie_browser.presentation.detailsScreen.DetailsScreen
import com.example.movie_browser.presentation.mainScreen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieBrowserTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    val navHost = rememberNavigationState()
                    val modifier = Modifier
                    modifier.padding(innerPadding)
                    MovieNavGraph(
                        navHostController = navHost.navHostController,
                        detailsScreenContent = {
                            LaunchedEffect(key1 = it) {
                                viewModel.loadMovieDetails(it)
                            }
                            DetailsScreen(
                                modifier = modifier,
                                viewModel = viewModel
                            )


                        },
                        homeScreenContent = {
                            MainScreen(
                                modifier,
                                viewModel,
                                onMovieClick = { itemid ->
                                    navHost.navigateToDetails(itemid)
                                })
                        },
                    )
                }
            }
        }
    }

}