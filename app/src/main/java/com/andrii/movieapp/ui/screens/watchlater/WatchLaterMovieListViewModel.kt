package com.andrii.movieapp.ui.screens.watchlater

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrii.movieapp.repositories.watchlater.WatchLaterMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchLaterMovieListViewModel @Inject constructor(
    private val repository: WatchLaterMovieRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<WatchLaterMovieListState>(WatchLaterMovieListState.Loading)
    val uiState: StateFlow<WatchLaterMovieListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository
                .watchLaterMovies
                .catch {
                    _uiState.value = WatchLaterMovieListState.Error(it)
                }
                .collect {
                    _uiState.value = WatchLaterMovieListState.Success(
                        movies = it,
                    )
                }
        }
        fetchMovies()
    }

    fun fetchMovies() {
        _uiState.value = WatchLaterMovieListState.Loading
        viewModelScope.launch {
            try {
                repository.fetchMovies()
            } catch (e: Throwable) {
                _uiState.value = WatchLaterMovieListState.Error(e)
            }
        }
    }
}