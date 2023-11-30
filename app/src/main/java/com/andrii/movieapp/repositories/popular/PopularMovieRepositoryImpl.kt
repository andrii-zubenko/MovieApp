package com.andrii.movieapp.repositories.popular

import com.andrii.movieapp.API_KEY
import com.andrii.movieapp.database.popular.PopularMovieDao
import com.andrii.movieapp.database.saved.SavedMovieDao
import com.andrii.movieapp.models.Movie
import com.andrii.movieapp.network.MovieService
import com.andrii.movieapp.prefs.MoviePrefs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PopularMovieRepositoryImpl(
    private val service: MovieService,
    private val prefs: MoviePrefs,
    private val popularMovieDao: PopularMovieDao,
    private val savedMovieDao: SavedMovieDao,
) : PopularMovieRepository {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var lastUpdatedDate: String = ""

    private val _movies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    override val popularMovies: Flow<List<Movie>> = _movies.asStateFlow()

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
            _movies.value = emptyList()
            val moviesResponse = service.getPopularMovies(API_KEY)

            popularMovieDao.deleteAllMovies()

            _movies.value = emptyList()
            _movies.value = if (moviesResponse.isSuccessful) {
                val movies = moviesResponse.body()!!.results.toMutableList()

                popularMovieDao.addMovies(*movies.toTypedArray())
                prefs.setLastUpdatedDate()
                movies
            } else {
                throw Throwable("Request failed: ${moviesResponse.errorBody()}")
            }
        } catch (e: Throwable) {
            _movies.value = emptyList()
            _movies.value = popularMovieDao.getAllMovies()
        }
    }

    override fun getMovie(index: Int): Movie? {
        return _movies.value.getOrNull(index)
    }

    override fun getLastUpdatedDate(): String {
        return lastUpdatedDate
    }

    override suspend fun addToWatchLater(movie: Movie) {
        movie.addedToWatched = false
        movie.addedToWatchLater = true
        savedMovieDao.addMovie(movie)
    }

    override suspend fun addToWatched(movie: Movie) {
        movie.addedToWatchLater = false
        movie.addedToWatched = true
        savedMovieDao.addMovie(movie)
    }
}
