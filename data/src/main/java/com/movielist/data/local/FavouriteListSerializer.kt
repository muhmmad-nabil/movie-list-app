package com.movielist.data.local

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.movielist.data.entity.FavouriteList
import java.io.InputStream
import java.io.OutputStream

object FavouriteListSerializer : Serializer<FavouriteList> {
    override val defaultValue: FavouriteList
        get() = FavouriteList.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FavouriteList = try {
        FavouriteList.parseFrom(input)
    } catch (exception: InvalidProtocolBufferException) {
        throw CorruptionException("Cannot read proto.", exception)
    }

    override suspend fun writeTo(t: FavouriteList, output: OutputStream) = t.writeTo(output)
}