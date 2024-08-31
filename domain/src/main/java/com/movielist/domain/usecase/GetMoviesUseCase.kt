package com.movielist.domain.usecase

import com.movielist.domain.entity.Movies
import com.movielist.domain.entity.Resource
import com.movielist.domain.repo.MoviesRepo

class GetMoviesUseCase(private val repo: MoviesRepo) {
    suspend operator fun invoke(): Resource<Movies> = repo.getMovies()
}