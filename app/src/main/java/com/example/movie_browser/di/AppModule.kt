package com.example.movie_browser.di

import android.content.Context
import com.example.movie_browser.data.RepositoryImpl
import com.example.movie_browser.data.db.FavouriteDatabase
import com.example.movie_browser.data.db.FavouriteMoviesDao
import com.example.movie_browser.data.network.ApiFactory
import com.example.movie_browser.data.network.ApiService
import com.example.movie_browser.domain.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): FavouriteDatabase {
        return FavouriteDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideDao(database: FavouriteDatabase): FavouriteMoviesDao {
        return database.favouriteCitiesDao()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiFactory.api
    }

    @Provides
    @Singleton
    fun provideMovieRepository(
        dao: FavouriteMoviesDao,
        apiService: ApiService
    ): Repository {
        return RepositoryImpl(dao, apiService)
    }
}