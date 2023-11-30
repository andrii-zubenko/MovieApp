package com.andrii.movieapp.di

import com.andrii.movieapp.database.popular.PopularMovieDatabase
import com.andrii.movieapp.database.saved.SavedMovieDatabase
import com.andrii.movieapp.network.MovieService
import com.andrii.movieapp.prefs.MoviePrefs
import com.andrii.movieapp.repositories.popular.PopularMovieRepository
import com.andrii.movieapp.repositories.popular.PopularMovieRepositoryImpl
import com.andrii.movieapp.repositories.saved.SavedMovieRepository
import com.andrii.movieapp.repositories.saved.SavedMovieRepositoryImpl
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
    fun providesPopularMovieRepository(
        service: MovieService,
        prefs: MoviePrefs,
        popularMovieDatabase: PopularMovieDatabase,
        savedMovieDatabase: SavedMovieDatabase
    ): PopularMovieRepository = PopularMovieRepositoryImpl(
        service,
        prefs,
        popularMovieDatabase.popularMovieDao(),
        savedMovieDatabase.watchedMovieDao(),
    )


    @Provides
    @Singleton
    fun providesWatchedMovieRepository(
        savedMovieDatabase: SavedMovieDatabase,
    ): SavedMovieRepository =
        SavedMovieRepositoryImpl(savedMovieDatabase.watchedMovieDao())

}
