package com.muhmmad.movielist.di

import com.muhmmad.movielist.domain.repo.MoviesRepo
import com.muhmmad.movielist.domain.use_case.GetFavouriteUseCase
import com.muhmmad.movielist.domain.use_case.GetMoviesUseCase
import com.muhmmad.movielist.domain.use_case.MakeMovieFavouriteUseCase
import com.muhmmad.movielist.domain.use_case.RemoveMovieFromFavouritesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetMoviesUseCase(repo: MoviesRepo): GetMoviesUseCase = GetMoviesUseCase(repo)

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