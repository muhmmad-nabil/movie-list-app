package com.muhmmad.movielist.domain.use_case

import com.muhmmad.movielist.domain.repo.MoviesRepo

class GetFavouriteUseCase(private val repo: MoviesRepo) {
    suspend operator fun invoke(): List<Int> = repo.getFavouriteMovies()
}