package com.andrii.movieapp.di

import com.andrii.movieapp.network.MovieService
import com.andrii.movieapp.repositories.MovieRepository
import com.andrii.movieapp.repositories.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    @Singleton
    fun providesMovieRepository(
        service: MovieService
    ): MovieRepository = MovieRepositoryImpl(service)
}
