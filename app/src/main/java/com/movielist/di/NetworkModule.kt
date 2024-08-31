package com.movielist.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.movielist.data.entity.FavouriteList
import com.movielist.data.local.FavouriteListSerializer
import com.movielist.data.local.LocalDataSource
import com.movielist.data.local.LocalDataSourceImpl
import com.movielist.data.remote.RetrofitClient
import com.movielist.data.remote.RetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


val Context.dataStore: DataStore<FavouriteList> by dataStore(
    fileName = "favourite.pb",
    serializer = FavouriteListSerializer
)

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofitDataSource(): RetrofitDataSource = RetrofitClient.retrofitDataSource(
        "https://api.themoviedb.org/"
    )

    @Provides
    @Singleton
    fun provideLocalDataSource(
        @ApplicationContext context: Context
    ): LocalDataSource = LocalDataSourceImpl(dataStore = context.dataStore)
}