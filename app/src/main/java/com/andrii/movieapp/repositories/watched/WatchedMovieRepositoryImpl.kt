package com.andrii.movieapp.repositories.watched

import com.andrii.movieapp.database.watched.WatchedMovieDao
import com.andrii.movieapp.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WatchedMovieRepositoryImpl(
    private val watchedMovieDao: WatchedMovieDao
) : WatchedMovieRepository {


    private val _watchedMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    override val watchedMovies = _watchedMovies.asStateFlow()

    override suspend fun fetchMovies() {
        _watchedMovies.value = watchedMovieDao.getAllMovies()
    }
}
