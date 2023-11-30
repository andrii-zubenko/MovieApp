package com.andrii.movieapp.repositories.popular

import com.andrii.movieapp.API_KEY
import com.andrii.movieapp.database.popular.PopularMovieDao
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
) : PopularMovieRepository {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var lastUpdatedDate: String = ""

    private val _popularMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())
    override val popularMovies: Flow<List<Movie>> = _popularMovies.asStateFlow()

    init {
        coroutineScope.launch {
            prefs.getLastUpdatedDate()
                .collect { lastUpdated ->
                    lastUpdatedDate = lastUpdated
                }
        }
    }

    override suspend fun fetchMovies() {
        val watchLaterMovies = popularMovieDao.getWatchLaterMovies()
        val watchedMovies = popularMovieDao.getWatchedMovies()

        try {
            val moviesResponse = service.getPopularMovies(API_KEY)

            popularMovieDao.deleteAllMovies()

            _popularMovies.value = emptyList()
            _popularMovies.value = if (moviesResponse.isSuccessful) {
                val movies = moviesResponse.body()!!.results.toMutableList()
                    .map { movie ->
                        movie.copy(
                            addedToWatchLater = watchLaterMovies.any { it.id == movie.id },
                            addedToWatched = watchedMovies.any { it.id == movie.id },
                        )
                    }
                popularMovieDao.addMovies(*movies.toTypedArray())
                prefs.setLastUpdatedDate()
                movies
            } else {
                throw Throwable("Request failed: ${moviesResponse.errorBody()}")
            }
        } catch (e: Throwable) {
            _popularMovies.value = emptyList()
            _popularMovies.value = popularMovieDao.getAllMovies()
        }
    }

    override fun getMovie(id: Long): Movie? {
        return _popularMovies.value.firstOrNull { it.id == id }
    }

    override fun getLastUpdatedDate(): String {
        return lastUpdatedDate
    }

    override suspend fun addToWatchLater(movie: Movie) {
        movie.addedToWatched = false
        movie.addedToWatchLater = true
        popularMovieDao.updateMovie(movie)
    }

    override suspend fun addToWatched(movie: Movie) {
        movie.addedToWatchLater = false
        movie.addedToWatched = true
        popularMovieDao.updateMovie(movie)
    }
}
