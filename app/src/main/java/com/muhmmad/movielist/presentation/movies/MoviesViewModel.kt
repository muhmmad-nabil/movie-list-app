package com.muhmmad.movielist.presentation.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhmmad.movielist.data.entity.Resource
import com.muhmmad.movielist.domain.use_case.GetMoviesUseCase
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.internal.wait

private const val TAG = "MoviesViewModel"

class MoviesViewModel(private val useCase: GetMoviesUseCase) : ViewModel() {
//    private val _state = MutableStateFlow()
//    val state = _state.asSharedFlow()

    fun getMovies(accessToken: String) {
        viewModelScope.launch(IO) {
            useCase.invoke(accessToken).collectLatest {
                when (it) {
                    is Resource.Loading -> Log.i(TAG, "getMovies isLoading")
                    is Resource.Success -> Log.i(TAG, "getMovies isSuccess , ${it.data}")
                    is Resource.Error -> Log.i(TAG, "getMovies isError , ${it.message}")
                }
            }
        }
    }
}