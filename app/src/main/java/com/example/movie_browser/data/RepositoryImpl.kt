package com.example.movie_browser.data

import com.example.movie_browser.data.db.FavouriteDatabase
import com.example.movie_browser.data.db.FavouriteMoviesDao
import com.example.movie_browser.data.mapper.toDbModel
import com.example.movie_browser.data.mapper.toDomain
import com.example.movie_browser.data.mapper.toDomainList
import com.example.movie_browser.data.mapper.toEntities
import com.example.movie_browser.data.network.ApiService
import com.example.movie_browser.domain.Repository
import com.example.movie_browser.domain.entity.Movie
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val dao: FavouriteMoviesDao,
    private val api : ApiService
    ) : Repository {

    override suspend fun addToFavourite(movie: Movie) {
        val movieDb = movie.toDbModel()
        dao.addToFavourite(movieDb)
    }

    override suspend fun removeFromFavourite(movieId: Int) {
        dao.removeFromFavourite(movieId)
    }

    override val favouriteMovies: Flow<List<Movie>> = dao.getFavouriteMovies().map {
        it.toEntities()
    }

    override fun observeIsFavourite(movieId: Int): Flow<Boolean> =
        dao.observeIsFavourite(movieId)

    override suspend fun getMovieDetails(movieId: Int): Movie {
        return api.getMovieDetails(movieId).toDomain()
    }

    override suspend fun getPopularMovies(): List<Movie> {
        return api.getPopularMovies().results.toDomainList()
    }
}