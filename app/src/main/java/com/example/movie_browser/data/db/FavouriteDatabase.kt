package com.example.movie_browser.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieDbModel::class], version = 1, exportSchema = false)
abstract class FavouriteDatabase : RoomDatabase(){


    abstract fun favouriteCitiesDao() : FavouriteMoviesDao
    companion object {
        private const val DB_NAME = "FavouriteDatabase"
        private var INSTANCE : FavouriteDatabase? = null
        private val LOCK = Any()
        fun getInstance(context: Context): FavouriteDatabase {
            INSTANCE?.let { return it }

            synchronized(LOCK) {
                INSTANCE?.let { return it }

                val database = Room.databaseBuilder(
                    context = context,
                    klass = FavouriteDatabase::class.java,
                    name = DB_NAME
                ).build()

                INSTANCE = database
                return database
            }

        }
    }
}