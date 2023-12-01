package com.andrii.movieapp.ui.screens.watchlater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrii.movieapp.models.WatchedStatus
import com.andrii.movieapp.repositories.popular.PopularMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchLaterMovieListViewModel @Inject constructor(
    private val popularMovieRepository: PopularMovieRepository,
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<WatchLaterMovieListState>(WatchLaterMovieListState.Loading)
    val uiState: StateFlow<WatchLaterMovieListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            popularMovieRepository
                .popularMovies
                .catch {
                    _uiState.value = WatchLaterMovieListState.Error(it)
                }
                .collect {
                    _uiState.value = WatchLaterMovieListState.Success(
                        movies = it.filter { movie ->
                            movie.watchedStatus == WatchedStatus.WATCH_LATER.statusString
                        },
                    )
                }
        }
        fetchMovies()
    }

    fun fetchMovies() {
        _uiState.value = WatchLaterMovieListState.Loading
        viewModelScope.launch {
            try {
                popularMovieRepository.fetchMovies()
            } catch (e: Throwable) {
                _uiState.value = WatchLaterMovieListState.Error(e)
            }
        }
    }
}