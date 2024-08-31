package com.movielist.domain.entity

import java.io.Serializable

data class Movie(
    val id: Int,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    var isFavourite: Boolean = false
) : Serializable