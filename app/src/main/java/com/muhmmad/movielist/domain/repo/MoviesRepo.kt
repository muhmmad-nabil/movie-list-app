package com.muhmmad.movielist.domain.repo

interface MoviesRepo {
    suspend fun getMovies()
    suspend fun getMoviesDetails()
    suspend fun makeMovieFavourite()
}