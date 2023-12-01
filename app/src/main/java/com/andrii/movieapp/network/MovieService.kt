package com.andrii.movieapp.network

import com.andrii.movieapp.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<ApiResponse>
}
