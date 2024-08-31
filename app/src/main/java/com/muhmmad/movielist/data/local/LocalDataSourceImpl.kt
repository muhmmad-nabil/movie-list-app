package com.muhmmad.movielist.data.local

import androidx.datastore.core.DataStore
import com.muhmmad.movielist.data.entity.FavouriteList
import com.muhmmad.movielist.data.entity.copy
import kotlinx.coroutines.flow.first

class LocalDataSourceImpl(private val dataStore: DataStore<FavouriteList>) : LocalDataSource {
    override suspend fun getFavouriteMovies(): List<Int> = dataStore.data.first().listList

    override suspend fun makeMovieFavourite(id: Int) {
        dataStore.updateData {
            it.copy {
                list.add(id)
            }
        }
    }

    override suspend fun removeMovieFromFavourites(id: Int) {
        dataStore.updateData { data ->
            FavouriteList.newBuilder().addAllList(data.listList.filter { it != id }).build()
        }
    }
}