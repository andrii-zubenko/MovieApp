package com.andrii.movieapp.database.watchlater

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import com.andrii.movieapp.models.Movie

private const val DATABASE_NAME = "watch_later_movie_database"
private const val DATABASE_VERSION = 1
@Database(
    entities = [Movie::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class WatchLaterMovieDatabase : RoomDatabase() {
    abstract fun watchLaterMovieDao(): WatchLaterMovieDao
    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            WatchLaterMovieDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}