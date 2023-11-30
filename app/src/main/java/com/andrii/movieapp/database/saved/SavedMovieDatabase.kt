package com.andrii.movieapp.database.saved

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
abstract class SavedMovieDatabase : RoomDatabase() {
    abstract fun watchedMovieDao(): SavedMovieDao
    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            SavedMovieDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}