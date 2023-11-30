package com.andrii.movieapp.di

import android.content.Context
import com.andrii.movieapp.network.MovieService
import com.andrii.movieapp.prefs.MoviePrefs
import com.andrii.movieapp.prefs.MoviePrefsImpl
import com.andrii.movieapp.repositories.MovieRepository
import com.andrii.movieapp.repositories.MovieRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SingletonModule {

    @Provides
    @Singleton
    fun providesMovieRepository(
        service: MovieService,
        prefs: MoviePrefs,
    ): MovieRepository = MovieRepositoryImpl(service, prefs)

    @Provides
    @Singleton
    fun provideMoviePrefs(@ApplicationContext applicationContext: Context): MoviePrefs {
        return MoviePrefsImpl(applicationContext)
    }
}
