package com.movielist.data.repo

import com.movielist.data.local.LocalDataSource
import com.movielist.data.remote.RetrofitDataSource
import com.movielist.data.utils.checkResponse
import com.movielist.data.utils.toLocalModel
import com.movielist.domain.entity.Movies
import com.movielist.domain.entity.Resource
import com.movielist.domain.repo.MoviesRepo


class MoviesRepoImpl(
    private val remoteDataSource: RetrofitDataSource,
    private val localDataSource: LocalDataSource
) : MoviesRepo {
    override suspend fun getMovies(): Resource<Movies> {
        return when (val result = remoteDataSource.getMovies().checkResponse()) {
            is Resource.Success -> Resource.Success(result.data?.toLocalModel()!!)
            is Resource.Error -> Resource.Error(result.message)
            is Resource.Loading -> Resource.Loading()
        }
    }

    override suspend fun getFavouriteMovies(): List<Int> = localDataSource.getFavouriteMovies()
    override suspend fun makeMovieFavourite(id: Int) = localDataSource.makeMovieFavourite(id)

    override suspend fun removeMovieFromFavourites(id: Int) =
        localDataSource.removeMovieFromFavourites(id)

}