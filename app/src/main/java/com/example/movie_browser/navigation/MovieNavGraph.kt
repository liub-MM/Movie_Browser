package com.example.movie_browser.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MovieNavGraph(
    navHostController: NavHostController,
    detailsScreenContent: @Composable (Int) -> Unit,
    homeScreenContent: @Composable () -> Unit,
    favouriteContent: @Composable () -> Unit,

    ) {

    NavHost(
        navHostController,
        startDestination = Screen.ROUTE_HOME
    ) {
        composable(Screen.ROUTE_DETAILS) { backStackEntry ->
            val movieIdStr = backStackEntry.arguments?.getString(Screen.KEY_MOVIE_ID)
            val movieId = movieIdStr?.toIntOrNull()
            if (movieId != null) {
                detailsScreenContent(movieId)
            }
        }
        composable(Screen.ROUTE_HOME) {
            homeScreenContent()
        }
        composable(Screen.ROUTE_FAVOURITE) {
            favouriteContent()
        }

    }
}