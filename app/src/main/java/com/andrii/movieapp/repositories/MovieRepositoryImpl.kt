package com.andrii.movieapp.repositories

import com.andrii.movieapp.API_KEY
import com.andrii.movieapp.database.MovieDao
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
    private val movieDao: MovieDao
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
        val watchedLater = movieDao.getWatchLaterMovies()
        val watched = movieDao.getWatchedMovies()

        try {
            _movies.value = emptyList()
            val moviesResponse = service.getPopularMovies(API_KEY)

            movieDao.deleteAllMovies()

            _movies.value = emptyList()
            _movies.value = if (moviesResponse.isSuccessful) {
                val movies = moviesResponse.body()!!.results.toMutableList()
                    .map { movie ->
                        movie.copy(
                            addedToWatchLater = watchedLater.any { it.id == movie.id },
                            addedToWatched = watched.any { it.id == movie.id }
                        )
                    }
                movieDao.addMovies(*movies.toTypedArray())
                prefs.setLastUpdatedDate()
                movies
            } else {
                throw Throwable("Request failed: ${moviesResponse.errorBody()}")
            }
        } catch (e: Throwable) {
            _movies.value = emptyList()
            _movies.value = movieDao.getAllMovies()
        }
    }

    override fun getMovie(index: Int): Movie? {
        return _movies.value.getOrNull(index)
    }

    override fun getLastUpdatedDate(): String {
        return lastUpdatedDate
    }

    override suspend fun addToWatchLater(movie: Movie) {
        val index = _movies.value.indexOf(movie)
        val mutableCountries = _movies.value.toMutableList()
        val updatedCountry = movie.copy(addedToWatchLater = movie.addedToWatchLater.not())
        mutableCountries[index] = updatedCountry

        movieDao.updateMovie(updatedCountry)

        _movies.value = mutableCountries.toList()
    }

    override suspend fun addToWatched(movie: Movie) {
        val index = _movies.value.indexOf(movie)
        val mutableCountries = _movies.value.toMutableList()
        val updatedCountry = movie.copy(addedToWatched = movie.addedToWatched.not())
        mutableCountries[index] = updatedCountry

        movieDao.updateMovie(updatedCountry)

        _movies.value = mutableCountries.toList()
    }
}
