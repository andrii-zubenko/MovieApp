package com.andrii.movieapp.repositories

import android.util.Log
import com.andrii.movieapp.database.PopularMovieDao
import com.andrii.movieapp.models.Movie
import com.andrii.movieapp.models.WatchedStatus
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
    private val popularMovieDao: PopularMovieDao
) : PopularMovieRepository {

    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private val _lastUpdatedDate = MutableStateFlow(0L)
    private val _isFromApi = MutableStateFlow(true)
    private val _popularMovies: MutableStateFlow<List<Movie>> = MutableStateFlow(emptyList())

    override val popularMovies: Flow<List<Movie>> = _popularMovies.asStateFlow()

    init {
        collectLastUpdatedFlow()
        collectIsFromApiFlow()
    }

    private fun collectLastUpdatedFlow() {
        coroutineScope.launch {
            prefs.getLastUpdatedDate()
                .collect { date ->
                    _lastUpdatedDate.value = date
                }
        }
    }

    private fun collectIsFromApiFlow() {
        coroutineScope.launch {
            prefs.getIsFromApiValue()
                .collect { isFromApi ->
                    _isFromApi.value = isFromApi
                }
        }
    }

    override suspend fun fetchMovies() {
        val watchLaterMovies = popularMovieDao.getWatchLaterMovies()
        val watchedMovies = popularMovieDao.getWatchedMovies()

        try {
            val moviesResponse = service.getPopularMovies()

            popularMovieDao.deleteAllMovies()

            _popularMovies.value = emptyList()
            _popularMovies.value = if (moviesResponse.isSuccessful) {
                val movies = moviesResponse.body()!!.results.toMutableList()
                    .map { movie ->
                        movie.copy(
                            watchedStatus = if (watchedMovies.contains(movie)) {
                                WatchedStatus.WATCHED.statusString
                            } else if (watchLaterMovies.contains(movie)) {
                                WatchedStatus.WATCH_LATER.statusString
                            } else {
                                WatchedStatus.NOT_WATCHED.statusString
                            }
                        )
                    }
                popularMovieDao.addMovies(*movies.toTypedArray())
                val currentTimestamp = System.currentTimeMillis()
                prefs.setLastUpdatedDate(currentTimestamp)
                prefs.setIsFromApiValue(true)
                movies.shuffled()
            } else {
                throw Throwable("Request failed: ${moviesResponse.errorBody()}")
            }
        } catch (e: Throwable) {
            Log.e("PopularMovieRepository", "Error: $e")
            _popularMovies.value = emptyList()
            _popularMovies.value = popularMovieDao.getAllMovies()
            prefs.setIsFromApiValue(false)
        }
        collectLastUpdatedFlow()
        collectIsFromApiFlow()
    }

    override fun getMovie(id: Long): Movie? {
        return _popularMovies.value.firstOrNull { it.id == id }
    }

    override fun getLastUpdatedDate(): Long {
        return _lastUpdatedDate.asStateFlow().value
    }

    override fun isFromApi(): Boolean {
        return _isFromApi.asStateFlow().value
    }

    override suspend fun addToWatchLater(movie: Movie) {
        movie.watchedStatus = WatchedStatus.WATCH_LATER.statusString
        popularMovieDao.updateMovie(movie)
    }

    override suspend fun addToWatched(movie: Movie) {
        movie.watchedStatus = WatchedStatus.WATCHED.statusString
        popularMovieDao.updateMovie(movie)
    }
}
