package com.example.movie_browser.data.mapper

import com.example.movie_browser.domain.entity.Movie
import com.example.moviebrowser.data.entityDto.MovieDto


fun MovieDto.toDomain(): Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage
)

fun List<MovieDto>.toDomainList(): List<Movie> = map { it.toDomain() }