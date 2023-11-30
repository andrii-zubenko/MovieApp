package com.andrii.movieapp.repositories.watchlater

import com.andrii.movieapp.database.watched.WatchedMovieDao
import com.andrii.movieapp.database.watchlater.WatchLaterMovieDao
import com.andrii.movieapp.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class WatchLaterMovieRepositoryImpl(
    private val watchLaterMovieDao: WatchLaterMovieDao
) : WatchLaterMovieRepository {


    private val _watchLaterMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    override val watchLaterMovies = _watchLaterMovies.asStateFlow()

    override suspend fun fetchMovies() {
        _watchLaterMovies.value = watchLaterMovieDao.getAllMovies()
    }
}