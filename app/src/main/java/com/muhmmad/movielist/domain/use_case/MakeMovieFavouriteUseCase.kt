package com.muhmmad.movielist.domain.use_case

import com.muhmmad.movielist.data.entity.Movie
import com.muhmmad.movielist.domain.repo.MoviesRepo

class MakeMovieFavouriteUseCase(private val repo: MoviesRepo) {
    suspend operator fun invoke(id: Int) = repo.makeMovieFavourite(id)
}