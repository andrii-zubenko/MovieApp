package com.andrii.movieapp.database.watched

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andrii.movieapp.models.Movie

@Dao
interface WatchedMovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)
}
