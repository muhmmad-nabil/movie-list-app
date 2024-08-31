package com.movielist.data.utils

import com.movielist.data.entity.MoviesResponse
import com.movielist.domain.entity.Movie
import com.movielist.domain.entity.Movies

fun MoviesResponse.toLocalModel(): Movies = Movies(results = results.map {
    Movie(
        id = it.id,
        originalLanguage = it.originalLanguage,
        overview = it.overview,
        popularity = it.popularity,
        posterPath = it.posterPath,
        releaseDate = it.releaseDate,
        title = it.title,
        voteAverage = it.voteAverage,
        isFavourite = false
    )
})