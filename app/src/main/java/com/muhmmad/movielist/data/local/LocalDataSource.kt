package com.muhmmad.movielist.data.local

import com.muhmmad.movielist.data.entity.Movie

interface LocalDataSource {
    suspend fun getFavouriteMovies(): List<Int>
    suspend fun makeMovieFavourite(id: Int)
    suspend fun removeMovieFromFavourites(id: Int)
}