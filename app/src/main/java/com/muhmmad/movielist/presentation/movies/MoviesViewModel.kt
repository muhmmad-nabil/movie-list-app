package com.muhmmad.movielist.presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhmmad.movielist.data.entity.Movie
import com.muhmmad.movielist.data.entity.Resource
import com.muhmmad.movielist.domain.use_case.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MoviesViewModel"

@HiltViewModel
class MoviesViewModel @Inject constructor(private val useCase: GetMoviesUseCase) : ViewModel() {
    private val _state = MutableStateFlow(MoviesUIState())
    val state = _state.asSharedFlow()

    fun getMovies(accessToken: String) {
        viewModelScope.launch(IO) {
            useCase.invoke("Bearer $accessToken").collectLatest { response ->
                when (response) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                movies = response.data?.results ?: emptyList()
                            )
                        }
                    }

                    is Resource.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                error = response.message.toString()
                            )
                        }
                    }
                }
            }
        }
    }

    data class MoviesUIState(
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList(),
        val error: String = ""
    )
}