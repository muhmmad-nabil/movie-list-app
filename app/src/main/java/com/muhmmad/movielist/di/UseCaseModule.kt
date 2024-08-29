package com.muhmmad.movielist.di

import com.muhmmad.movielist.domain.repo.MoviesRepo
import com.muhmmad.movielist.domain.use_case.GetMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetMoviesUseCase(repo: MoviesRepo): GetMoviesUseCase = GetMoviesUseCase(repo)
}