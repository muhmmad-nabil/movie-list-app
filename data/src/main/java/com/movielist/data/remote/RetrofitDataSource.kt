package com.movielist.data.remote

import com.movielist.data.BuildConfig
import com.movielist.data.entity.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitDataSource {
    @GET("3/discover/movie?primary_release_year=2024&sort_by=vote_average.desc")
    suspend fun getMovies(@Header("Authorization") accessToken: String = BuildConfig.ACCESS_TOKEN): Response<MoviesResponse>
}