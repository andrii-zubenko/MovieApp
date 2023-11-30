package com.andrii.movieapp.di

import com.andrii.movieapp.database.MovieDatabase
import com.andrii.movieapp.network.MovieService
import com.andrii.movieapp.prefs.MoviePrefs
import com.andrii.movieapp.repositories.MovieRepository
import com.andrii.movieapp.repositories.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesMovieRepository(
        service: MovieService,
        prefs: MoviePrefs,
        database: MovieDatabase,
    ): MovieRepository = MovieRepositoryImpl(service, prefs, database.movieDao())

}
