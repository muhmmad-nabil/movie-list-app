package com.muhmmad.movielist.domain.use_case

import com.muhmmad.movielist.data.entity.MoviesResponse
import com.muhmmad.movielist.data.entity.Resource
import com.muhmmad.movielist.domain.checkResponse
import com.muhmmad.movielist.domain.repo.MoviesRepo
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

class GetMoviesUseCase(private val repo: MoviesRepo) {
    operator fun invoke(accessToken: String): Flow<Resource<MoviesResponse>> =
        flow {
            emit(Resource.Loading())
            emit(repo.getMovies(accessToken).checkResponse())
        }
}