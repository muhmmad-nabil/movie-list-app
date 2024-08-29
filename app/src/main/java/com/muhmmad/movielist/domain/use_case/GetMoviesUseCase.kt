package com.muhmmad.movielist.domain.use_case

import com.muhmmad.movielist.domain.repo.MoviesRepo

class GetMoviesUseCase(private val repo: MoviesRepo) {
    suspend operator fun invoke() = repo.getMovies()
}