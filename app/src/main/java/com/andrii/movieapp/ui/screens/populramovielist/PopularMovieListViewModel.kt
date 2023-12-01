package com.andrii.movieapp.ui.screens.populramovielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrii.movieapp.repositories.PopularMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMovieListViewModel @Inject constructor(
    private val repository: PopularMovieRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PopularMovieListState>(PopularMovieListState.Loading)
    val uiState: StateFlow<PopularMovieListState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository
                .popularMovies
                .catch {
                    _uiState.value = PopularMovieListState.Error(it)
                }
                .collect {
                    _uiState.value = PopularMovieListState.Success(
                        movies = it,
                        lastUpdatedDate = repository.getLastUpdatedDate()
                    )
                }
        }

        fetchMovies()
    }

    fun fetchMovies() {
        _uiState.value = PopularMovieListState.Loading
        viewModelScope.launch {
            try {
                repository.fetchMovies()
            } catch (e: Throwable) {
                _uiState.value = PopularMovieListState.Error(e)
            }
        }
    }
}
