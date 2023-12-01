package com.andrii.movieapp.di

import android.content.Context
import com.andrii.movieapp.database.PopularMovieDatabase
import com.andrii.movieapp.prefs.MoviePrefs
import com.andrii.movieapp.prefs.MoviePrefsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    @Singleton
    fun provideMoviePrefs(@ApplicationContext applicationContext: Context): MoviePrefs {
        return MoviePrefsImpl(applicationContext)
    }

    @Provides
    @Singleton
    fun providePopularMovieDatabase(@ApplicationContext context: Context): PopularMovieDatabase {
        return PopularMovieDatabase.buildDatabase(context)
    }
}
