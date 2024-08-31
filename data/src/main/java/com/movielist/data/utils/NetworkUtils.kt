package com.movielist.data.utils

import com.movielist.domain.entity.Resource
import com.movielist.domain.entity.Resource.Success
import com.movielist.domain.entity.Resource.Error
import retrofit2.Response

fun <T> Response<T>.checkResponse(): Resource<T> {
    if (this.isSuccessful) return Success(data = this.body()!!)
    else return Error(message = this.code().toString())
}