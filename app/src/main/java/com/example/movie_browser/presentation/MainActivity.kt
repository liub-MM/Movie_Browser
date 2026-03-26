package com.example.movie_browser.presentation

import MovieBrowserTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movie_browser.navigation.MovieNavGraph
import com.example.movie_browser.navigation.Screen
import com.example.movie_browser.navigation.rememberNavigationState
import com.example.movie_browser.presentation.detailsScreen.DetailsScreen
import com.example.movie_browser.presentation.favouriteScreen.FavouriteScreen
import com.example.movie_browser.presentation.mainScreen.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MovieViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHost = rememberNavigationState()

            val navBackStackEntry by navHost.navHostController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            MovieBrowserTheme {
                Scaffold(modifier = Modifier.Companion.fillMaxSize(), bottomBar = {
                    if (currentRoute == Screen.ROUTE_HOME || currentRoute == Screen.ROUTE_FAVOURITE) {
                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Home, contentDescription = "Головна") },
                                label = { Text("Популярні") },
                                selected = currentRoute == Screen.ROUTE_HOME,
                                onClick = { navHost.navigateToHome() }
                            )
                            NavigationBarItem(
                                icon = {
                                    Icon(
                                        Icons.Default.Favorite,
                                        contentDescription = "Обране"
                                    )
                                },
                                label = { Text("Обране") },
                                selected = currentRoute == Screen.ROUTE_FAVOURITE,
                                onClick = { navHost.navigateToFavourite() }
                            )
                        }
                    }
                }) { innerPadding ->
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
                                viewModel = viewModel,
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
                        favouriteContent = {
                            FavouriteScreen(viewModel = viewModel) {
                                navHost.navigateToDetails(it)
                            }
                        }
                    )
                }
            }
        }
    }

}