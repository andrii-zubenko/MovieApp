package com.andrii.movieapp.di

import com.andrii.movieapp.models.ApiResponse
import com.andrii.movieapp.network.MovieService
import com.andrii.movieapp.sampledata.sampleMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Response
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class NetworkTestModule {

    @Provides
    @Singleton
    fun provideMovieService(): MovieService = object : MovieService {
        override suspend fun getPopularMovies(): Response<ApiResponse> {
            return Response.success(ApiResponse(sampleMovies))
        }
    }
}
