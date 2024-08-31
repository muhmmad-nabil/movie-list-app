package com.movielist.di

import com.movielist.domain.repo.MoviesRepo
import com.movielist.domain.usecase.GetFavouriteUseCase
import com.movielist.domain.usecase.GetMoviesUseCase
import com.movielist.domain.usecase.MakeMovieFavouriteUseCase
import com.movielist.domain.usecase.RemoveMovieFromFavouritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetMoviesUseCase(repo: MoviesRepo): GetMoviesUseCase =
        GetMoviesUseCase(repo)

    @Provides
    fun provideGetFavouriteUseCase(repo: MoviesRepo): GetFavouriteUseCase =
        GetFavouriteUseCase(repo)

    @Provides
    fun provideMakeMovieFavouriteUseCase(repo: MoviesRepo): MakeMovieFavouriteUseCase =
        MakeMovieFavouriteUseCase(repo)

    @Provides
    fun provideRemoveMovieFromFavouritesUseCase(repo: MoviesRepo): RemoveMovieFromFavouritesUseCase =
        RemoveMovieFromFavouritesUseCase(repo)
}