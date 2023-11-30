package com.andrii.movieapp.repositories

import com.andrii.movieapp.API_KEY
import com.andrii.movieapp.models.Movie
import com.andrii.movieapp.network.MovieService
import com.andrii.movieapp.prefs.MoviePrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieRepositoryImpl(
    private val service: MovieService,
    private val prefs: MoviePrefs,
) : MovieRepository {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var lastUpdatedDate: String = ""

    private val _movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    override val movies: Flow<List<Movie>> = _movies.asStateFlow()

    init {
        coroutineScope.launch {
            prefs.getLastUpdatedDate()
                .collect { lastUpdated ->
                    lastUpdatedDate = lastUpdated
                }
        }
    }

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
            prefs.setLastUpdatedDate()
        } catch (e: Throwable) {
            throw Throwable("Request failed: ${e.message}")
        }
    }

    override fun getMovie(index: Int): Movie? {
        return _movies.value.getOrNull(index)
    }

    override fun getLastUpdatedDate(): String {
        return lastUpdatedDate
    }
}
