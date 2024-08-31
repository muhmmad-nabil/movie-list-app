package com.muhmmad.movielist.di

import com.muhmmad.movielist.data.local.LocalDataSource
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
    fun provideMoviesRepo(
        remoteDataSource: RetrofitDataSource,
        localDataSource: LocalDataSource
    ): MoviesRepo = MoviesRepoImpl(remoteDataSource, localDataSource)
}