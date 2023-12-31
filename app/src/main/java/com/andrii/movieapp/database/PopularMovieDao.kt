package com.andrii.movieapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.andrii.movieapp.models.Movie

@Dao
interface PopularMovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovies(vararg movie: Movie)

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE watchedStatus = 'watched'")
    suspend fun getWatchedMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE watchedStatus = 'watch_later'")
    suspend fun getWatchLaterMovies(): List<Movie>

    @Update
    suspend fun updateMovie(movie: Movie)

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()
}
