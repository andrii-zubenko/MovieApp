package com.andrii.movieapp.repositories.saved

import com.andrii.movieapp.database.saved.SavedMovieDao
import com.andrii.movieapp.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SavedMovieRepositoryImpl(
    private val savedMovieDao: SavedMovieDao
) : SavedMovieRepository {


    private val _watchedMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    override val watchedMovies = _watchedMovies.asStateFlow()

    private val _watchLaterMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    override val watchLaterMovies = _watchLaterMovies.asStateFlow()

    override suspend fun fetchWatchedMovies() {
        _watchedMovies.value = savedMovieDao.getWatchedMovies()
    }

    override suspend fun fetchWatchLaterMovies() {
        _watchLaterMovies.value = savedMovieDao.getWatchLaterMovies()
    }
}
