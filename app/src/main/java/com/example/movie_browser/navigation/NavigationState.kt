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

    fun navigateBack() {
        navHostController.popBackStack()
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