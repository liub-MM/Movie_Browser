package com.example.movie_browser.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteMoviesDao {

    @Query("SELECT * FROM favourite_movies")
    fun getFavouriteMovies() : Flow<List<MovieDbModel>>

    @Query("SELECT EXISTS (SELECT * FROM favourite_movies WHERE id =:movieId LIMIT 1)")
    fun observeIsFavourite(movieId : Int) : Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavourite(cityDbModel: MovieDbModel)

    @Query("DELETE FROM favourite_movies WHERE id=:movieId")
    suspend fun removeFromFavourite(movieId : Int)

}