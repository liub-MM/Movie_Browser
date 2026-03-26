package com.example.movie_browser.navigation

sealed class Screen(val route: String) {

    object Home : Screen(ROUTE_HOME)
    object Details : Screen(ROUTE_DETAILS) {
        private const val ROUTE_FOR_ARGS = "details"

        fun getRouteWithArgs(movieId: Int): String {
            return "$ROUTE_FOR_ARGS/$movieId"
        }
    }
    object Favourite : Screen(ROUTE_FAVOURITE)

    companion object {
        const val  ROUTE_FAVOURITE= "favourite"
        const val ROUTE_HOME = "home"
        const val KEY_MOVIE_ID = "movieId"
        const val ROUTE_DETAILS = "details/{$KEY_MOVIE_ID}"
    }
}