package com.muhmmad.movielist.data.repo

import com.muhmmad.movielist.data.local.LocalDataSource
import com.muhmmad.movielist.data.remote.RetrofitDataSource
import com.muhmmad.movielist.domain.repo.MoviesRepo

class MoviesRepoImpl(
    private val remoteDataSource: RetrofitDataSource,
    private val localDataSource: LocalDataSource
) : MoviesRepo {
    override suspend fun getMovies(accessToken: String) = remoteDataSource.getMovies(accessToken)

    override suspend fun getFavouriteMovies(): List<Int> = localDataSource.getFavouriteMovies()
    override suspend fun makeMovieFavourite(id: Int) = localDataSource.makeMovieFavourite(id)

    override suspend fun removeMovieFromFavourites(id: Int) =
        localDataSource.removeMovieFromFavourites(id)

}