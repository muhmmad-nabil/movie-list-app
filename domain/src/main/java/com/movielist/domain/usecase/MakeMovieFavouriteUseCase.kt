package com.movielist.domain.usecase

import com.movielist.domain.repo.MoviesRepo

class MakeMovieFavouriteUseCase(private val repo: MoviesRepo) {
    suspend operator fun invoke(id: Int) = repo.makeMovieFavourite(id)
}