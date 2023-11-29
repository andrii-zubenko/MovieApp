package com.andrii.movieapp.ui.screens.moviedetails

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.andrii.movieapp.models.Movie
import com.andrii.movieapp.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MovieRepository
): ViewModel() {

    private var _selectedMovie: MutableState<Movie?> = mutableStateOf(null)
    val selectedMovie = _selectedMovie

    fun getSelectedMovie(movieIndex: Int) {
        _selectedMovie.value = repository.getMovie(movieIndex)
    }
}
