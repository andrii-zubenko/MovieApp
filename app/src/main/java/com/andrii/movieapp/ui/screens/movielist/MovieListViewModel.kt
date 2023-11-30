package com.andrii.movieapp.ui.screens.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrii.movieapp.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<MovieListState>(MovieListState.Loading)
    val uiState: StateFlow<MovieListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository
                .movies
                .catch {
                    _uiState.value = MovieListState.Error(it)
                }
                .collect {
                    _uiState.value = MovieListState.Success(
                        movies = it,
                        lastUpdatedDate = repository.getLastUpdatedDate(),
                    )
                }
        }

        fetchMovies()
    }

    fun fetchMovies() {
        _uiState.value = MovieListState.Loading
        viewModelScope.launch {
            try {
                delay(2000L)
                repository.fetchMovies()
            } catch (e: Throwable) {
                _uiState.value = MovieListState.Error(e)
            }
        }
    }
}
