package com.andrii.movieapp.rules.ui.screens.popularmovielist

import app.cash.turbine.test
import com.andrii.movieapp.models.Movie
import com.andrii.movieapp.repositories.PopularMovieRepository
import com.andrii.movieapp.rules.MainDispatcherRule
import com.andrii.movieapp.sampledata.sampleMovies
import com.andrii.movieapp.ui.screens.populramovielist.PopularMovieListState
import com.andrii.movieapp.ui.screens.populramovielist.PopularMovieListViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test

class PopularMovieListViewModelTests {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `init returns expected data`() = runTest {
        // Arrange
        val mockRepository = mockk<PopularMovieRepository>()
        val movieFlow = MutableStateFlow<List<Movie>>(emptyList())

        every { mockRepository.popularMovies } returns movieFlow
        every { mockRepository.getLastUpdatedDate() } returns 0L
        every { mockRepository.isFromApi() } returns true

        val expectedMovies = sampleMovies

        coEvery { mockRepository.fetchMovies() } coAnswers {
            delay(500); movieFlow.value = expectedMovies
        }

        // Act
        val sut = PopularMovieListViewModel(mockRepository)

        // Assert
        sut.uiState.test {
            assertThat(awaitItem()).isEqualTo(PopularMovieListState.Loading)
            assertThat(awaitItem()).isEqualTo(
                PopularMovieListState.Success(
                    expectedMovies,
                    0L,
                    true
                )
            )
        }
    }
}
