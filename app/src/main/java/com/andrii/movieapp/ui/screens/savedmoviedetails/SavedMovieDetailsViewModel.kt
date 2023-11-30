package com.andrii.movieapp.ui.screens.savedmoviedetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrii.movieapp.models.Movie
import com.andrii.movieapp.repositories.saved.SavedMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedMovieDetailsViewModel @Inject constructor(
    private val savedMovieRepository: SavedMovieRepository
): ViewModel() {

    private var _selectedWatchedMovie: MutableState<Movie?> = mutableStateOf(null)
    val selectedWatchedMovie = _selectedWatchedMovie

    private var _selectedWatchLaterMovie: MutableState<Movie?> = mutableStateOf(null)
    val selectedWatchLaterMovie = _selectedWatchLaterMovie

    fun getSelectedWatchedMovie(movieIndex: Int) {
        _selectedWatchedMovie.value = savedMovieRepository.getWatchedMovie(movieIndex)
    }

    fun getSelectedWatchLaterMovie(movieIndex: Int) {
        _selectedWatchLaterMovie.value = savedMovieRepository.getWatchLaterMovie(movieIndex)
    }

    fun addToWatchLater() {
        viewModelScope.launch {
            selectedWatchLaterMovie.value?.let {
                // TODO
            }
        }
    }

    fun addToWatched() {
        viewModelScope.launch {
            selectedWatchedMovie.value?.let {
                // TODO
            }
        }
    }
}
