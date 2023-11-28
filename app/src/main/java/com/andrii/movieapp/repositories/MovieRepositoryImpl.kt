package com.andrii.movieapp.repositories

import com.andrii.movieapp.API_KEY
import com.andrii.movieapp.models.Movie
import com.andrii.movieapp.network.MovieService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieRepositoryImpl(
    private val service: MovieService
) : MovieRepository {

    private val _movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    override val movies: Flow<List<Movie>> = _movies.asStateFlow()

    override suspend fun fetchMovies() {
        try {
            val moviesResponse = service.getPopularMovies(API_KEY)

            _movies.value = emptyList()
            _movies.value = if (moviesResponse.isSuccessful) {
                val movies = moviesResponse.body()!!.results.toMutableList()
                movies
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            throw Throwable("Request failed: ${e.message}")
        }
    }
}