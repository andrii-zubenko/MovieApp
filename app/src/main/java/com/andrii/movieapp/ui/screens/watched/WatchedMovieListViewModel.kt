package com.andrii.movieapp.ui.screens.watched

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrii.movieapp.repositories.watched.WatchedMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchedMovieListViewModel @Inject constructor(
    private val repository: WatchedMovieRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<WatchedMovieListState>(WatchedMovieListState.Loading)
    val uiState: StateFlow<WatchedMovieListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository
                .watchedMovies
                .catch {
                    _uiState.value = WatchedMovieListState.Error(it)
                }
                .collect {
                    _uiState.value = WatchedMovieListState.Success(
                        movies = it,
                    )
                }
        }
        fetchMovies()
    }

    fun fetchMovies() {
        _uiState.value = WatchedMovieListState.Loading
        viewModelScope.launch {
            try {
                repository.fetchMovies()
            } catch (e: Throwable) {
                _uiState.value = WatchedMovieListState.Error(e)
            }
        }
    }

}
