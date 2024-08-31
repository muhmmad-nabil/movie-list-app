package com.movielist.di

import com.movielist.data.local.LocalDataSource
import com.movielist.data.remote.RetrofitDataSource
import com.movielist.data.repo.MoviesRepoImpl
import com.movielist.domain.repo.MoviesRepo
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