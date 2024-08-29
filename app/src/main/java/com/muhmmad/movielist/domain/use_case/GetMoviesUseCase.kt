package com.muhmmad.movielist.domain.use_case

import com.muhmmad.movielist.data.entity.MoviesResponse
import com.muhmmad.movielist.data.entity.Resource
import com.muhmmad.movielist.domain.checkResponse
import com.muhmmad.movielist.domain.repo.MoviesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class GetMoviesUseCase(private val repo: MoviesRepo) {
    operator fun invoke(accessToken: String): Flow<Resource<MoviesResponse>> =
        callbackFlow {
            trySend(Resource.Loading())
            trySend(repo.getMovies(accessToken).checkResponse())
        }
}