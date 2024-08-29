package com.muhmmad.movielist.di

import com.muhmmad.movielist.data.remote.RetrofitDataSource
import com.muhmmad.movielist.data.repo.MoviesRepoImpl
import com.muhmmad.movielist.domain.repo.MoviesRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun provideMoviesRepo(dataSource: RetrofitDataSource): MoviesRepo = MoviesRepoImpl(dataSource)
}