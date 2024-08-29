package com.muhmmad.movielist.di

import com.muhmmad.movielist.data.remote.RetrofitClient
import com.muhmmad.movielist.data.remote.RetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitDataSource(): RetrofitDataSource = RetrofitClient.retrofitDataSource(
        "https://api.themoviedb.org/"
    )
}