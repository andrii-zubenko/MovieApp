package com.andrii.movieapp.database.watched

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andrii.movieapp.models.Movie

private const val DATABASE_NAME = "watched_movie_database"
private const val DATABASE_VERSION = 1
@Database(
    entities = [Movie::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class WatchedMovieDatabase : RoomDatabase() {
    abstract fun watchedMovieDao(): WatchedMovieDao
    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            WatchedMovieDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}