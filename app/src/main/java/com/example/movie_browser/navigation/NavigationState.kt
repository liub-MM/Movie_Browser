package com.example.movie_browser.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import javax.inject.Inject

class MovieNavigationState @Inject constructor(val navHostController: NavHostController) {
    fun navigateToDetails(movieId: Int) {
        navHostController.navigate(Screen.Details.getRouteWithArgs(movieId))
    }
    fun navigateToFavourite() {
        navHostController.navigate(Screen.Favourite.route) {
            popUpTo(Screen.Home.route)
            launchSingleTop = true
        }
    }
    fun navigateToHome() {
        navHostController.navigate(Screen.Home.route) {
            popUpTo(Screen.Home.route)
            launchSingleTop = true
        }
    }

}

@Composable
fun rememberNavigationState(
    navController: NavHostController = rememberNavController(),
): MovieNavigationState {
    return remember {
        MovieNavigationState(navController)
    }
}