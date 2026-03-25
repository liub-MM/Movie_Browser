package com.example.movie_browser.data.mapper

import com.example.movie_browser.data.db.MovieDbModel
import com.example.movie_browser.domain.entity.Movie
import com.example.moviebrowser.data.entityDto.MovieDto


fun Movie.toDbModel(): MovieDbModel = MovieDbModel(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage
)


fun MovieDbModel.toEntity() : Movie = Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage
)
fun List<MovieDbModel>.toEntities () : List<Movie> = map { it.toEntity() }