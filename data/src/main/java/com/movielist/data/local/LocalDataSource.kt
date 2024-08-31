package com.movielist.data.local

interface LocalDataSource {
    suspend fun getFavouriteMovies(): List<Int>
    suspend fun makeMovieFavourite(id: Int)
    suspend fun removeMovieFromFavourites(id: Int)
}