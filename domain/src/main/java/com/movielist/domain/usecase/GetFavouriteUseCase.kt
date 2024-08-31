package com.movielist.domain.usecase

import com.movielist.domain.repo.MoviesRepo

class GetFavouriteUseCase(private val repo: MoviesRepo) {
    suspend operator fun invoke(): List<Int> = repo.getFavouriteMovies()
}