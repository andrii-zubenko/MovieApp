package com.andrii.movieapp.ui.screens.moviedetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrii.movieapp.models.Movie
import com.andrii.movieapp.repositories.PopularMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val popularMovieRepository: PopularMovieRepository
) : ViewModel() {

    private var _selectedMovie: MutableState<Movie?> = mutableStateOf(null)
    val selectedMovie = _selectedMovie

    fun getSelectedMovie(movieId: Long) {
        _selectedMovie.value = popularMovieRepository.getMovie(movieId)
    }

    fun addToWatchLater() {
        viewModelScope.launch {
            selectedMovie.value?.let {
                popularMovieRepository.addToWatchLater(it)
            }
        }
    }

    fun addToWatched() {
        viewModelScope.launch {
            selectedMovie.value?.let {
                popularMovieRepository.addToWatched(it)
            }
        }
    }
}
