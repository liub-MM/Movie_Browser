package com.example.movie_browser.utils

fun formatReleaseYear(releaseDate: String?): String {
    if (releaseDate.isNullOrEmpty()) return ""
    return releaseDate.take(4)
}