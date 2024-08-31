package com.muhmmad.movielist.domain.repo

import com.muhmmad.movielist.data.entity.MoviesResponse
import retrofit2.Response

interface MoviesRepo {
    suspend fun getMovies(accessToken: String): Response<MoviesResponse>
    suspend fun getFavouriteMovies(): List<Int>
    suspend fun makeMovieFavourite(id: Int)
    suspend fun removeMovieFromFavourites(id: Int)
}