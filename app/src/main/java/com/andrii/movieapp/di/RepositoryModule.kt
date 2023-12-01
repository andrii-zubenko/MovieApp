package com.andrii.movieapp.di

import com.andrii.movieapp.database.PopularMovieDatabase
import com.andrii.movieapp.network.MovieService
import com.andrii.movieapp.prefs.MoviePrefs
import com.andrii.movieapp.repositories.PopularMovieRepository
import com.andrii.movieapp.repositories.PopularMovieRepositoryImpl
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
        popularMovieDatabase: PopularMovieDatabase
    ): PopularMovieRepository = PopularMovieRepositoryImpl(
        service,
        prefs,
        popularMovieDatabase.popularMovieDao()
    )
}
