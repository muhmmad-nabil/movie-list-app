package com.muhmmad.movielist.data.repo

import com.muhmmad.movielist.data.remote.RetrofitDataSource
import com.muhmmad.movielist.domain.repo.MoviesRepo

class MoviesRepoImpl(
    private val remoteDataSource: RetrofitDataSource
) : MoviesRepo {
    override suspend fun getMovies(accessToken: String) = remoteDataSource.getMovies(accessToken)

    override suspend fun getMoviesDetails() {

    }

    override suspend fun makeMovieFavourite() {

    }
}