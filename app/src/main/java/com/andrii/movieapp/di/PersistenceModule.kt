package com.andrii.movieapp.di

import android.content.Context
import com.andrii.movieapp.database.MovieDatabase
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
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return MovieDatabase.buildDatabase(context)
    }
}
