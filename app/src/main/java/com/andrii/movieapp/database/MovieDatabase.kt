package com.andrii.movieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.andrii.movieapp.models.Movie

private const val DATABASE_NAME = "movie_database"
private const val DATABASE_VERSION = 1

@Database(
    entities = [Movie::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            MovieDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}
