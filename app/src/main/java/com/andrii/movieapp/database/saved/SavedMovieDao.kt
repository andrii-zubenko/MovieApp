package com.andrii.movieapp.database.saved

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.andrii.movieapp.models.Movie

@Dao
interface SavedMovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE addedToWatched = 1")
    suspend fun getWatchedMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE addedToWatchLater = 1")
    suspend fun getWatchLaterMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: Movie)

    @Update
    suspend fun updateMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)
}
