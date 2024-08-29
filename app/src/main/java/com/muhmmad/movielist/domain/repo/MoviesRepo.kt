package com.muhmmad.movielist.domain.repo

import com.muhmmad.movielist.data.entity.MoviesResponse
import com.muhmmad.movielist.data.entity.Resource
import retrofit2.Response

interface MoviesRepo {
    suspend fun getMovies(accessToken: String): Response<MoviesResponse>
    suspend fun getMoviesDetails()
    suspend fun makeMovieFavourite()
}