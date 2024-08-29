package com.muhmmad.movielist.domain

import com.muhmmad.movielist.data.entity.Resource
import retrofit2.Response

fun <T> Response<T>.checkResponse(): Resource<T> {
    if (this.isSuccessful) return Resource.Success<T>(data = this.body()!!)
    else return Resource.Error<T>(message = this.message())
}