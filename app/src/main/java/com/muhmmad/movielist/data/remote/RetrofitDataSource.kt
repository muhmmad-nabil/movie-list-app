package com.muhmmad.movielist.data.remote

import com.muhmmad.movielist.data.entity.MoviesResponse
import com.muhmmad.movielist.data.entity.Resource
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitDataSource {
    @GET("3/discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    suspend fun getMovies(@Header("Authorization") accessToken: String): Response<MoviesResponse>
}