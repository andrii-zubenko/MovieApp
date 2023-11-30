package com.andrii.movieapp.di

import android.content.Context
import com.andrii.movieapp.database.popular.PopularMovieDatabase
import com.andrii.movieapp.database.watched.WatchedMovieDatabase
import com.andrii.movieapp.database.watchlater.WatchLaterMovieDatabase
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

    @Provides
    @Singleton
    fun provideWatchLaterMovieDatabase(@ApplicationContext context: Context): WatchLaterMovieDatabase {
        return WatchLaterMovieDatabase.buildDatabase(context)
    }

    @Provides
    @Singleton
    fun provideWatchedMovieDatabase(@ApplicationContext context: Context): WatchedMovieDatabase {
        return WatchedMovieDatabase.buildDatabase(context)
    }

}
