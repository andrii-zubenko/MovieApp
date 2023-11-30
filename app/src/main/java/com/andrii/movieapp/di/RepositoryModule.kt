package com.andrii.movieapp.di

import com.andrii.movieapp.database.popular.PopularMovieDatabase
import com.andrii.movieapp.database.watched.WatchedMovieDatabase
import com.andrii.movieapp.database.watchlater.WatchLaterMovieDatabase
import com.andrii.movieapp.network.MovieService
import com.andrii.movieapp.prefs.MoviePrefs
import com.andrii.movieapp.repositories.popular.PopularMovieRepository
import com.andrii.movieapp.repositories.popular.PopularMovieRepositoryImpl
import com.andrii.movieapp.repositories.watched.WatchedMovieRepository
import com.andrii.movieapp.repositories.watched.WatchedMovieRepositoryImpl
import com.andrii.movieapp.repositories.watchlater.WatchLaterMovieRepository
import com.andrii.movieapp.repositories.watchlater.WatchLaterMovieRepositoryImpl
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
        watchLaterMovieDatabase: WatchLaterMovieDatabase,
        watchedMovieDatabase: WatchedMovieDatabase
    ): PopularMovieRepository = PopularMovieRepositoryImpl(
        service,
        prefs,
        popularMovieDatabase.popularMovieDao(),
        watchLaterMovieDatabase.watchLaterMovieDao(),
        watchedMovieDatabase.watchedMovieDao(),
    )

    @Provides
    @Singleton
    fun providesWatchLaterMovieRepository(
        watchLaterMovieDatabase: WatchLaterMovieDatabase,
    ): WatchLaterMovieRepository =
        WatchLaterMovieRepositoryImpl(watchLaterMovieDatabase.watchLaterMovieDao())

    @Provides
    @Singleton
    fun providesWatchedMovieRepository(
        watchedMovieDatabase: WatchedMovieDatabase,
    ): WatchedMovieRepository =
        WatchedMovieRepositoryImpl(watchedMovieDatabase.watchedMovieDao())

}
