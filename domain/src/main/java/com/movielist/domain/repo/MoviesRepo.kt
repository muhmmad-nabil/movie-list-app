package com.movielist.domain.repo

import com.movielist.domain.entity.Movies
import com.movielist.domain.entity.Resource

interface MoviesRepo {
    suspend fun getMovies(): Resource<Movies>
    suspend fun getFavouriteMovies(): List<Int>
    suspend fun makeMovieFavourite(id: Int)
    suspend fun removeMovieFromFavourites(id: Int)
}