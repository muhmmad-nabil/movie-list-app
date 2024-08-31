package com.muhmmad.movielist.presentation.movies

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhmmad.movielist.data.entity.Movie
import com.muhmmad.movielist.data.entity.Resource
import com.muhmmad.movielist.domain.use_case.GetFavouriteUseCase
import com.muhmmad.movielist.domain.use_case.GetMoviesUseCase
import com.muhmmad.movielist.domain.use_case.MakeMovieFavouriteUseCase
import com.muhmmad.movielist.domain.use_case.RemoveMovieFromFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MoviesViewModel"

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val useCase: GetMoviesUseCase,
    private val getFavouriteUseCase: GetFavouriteUseCase,
    private val removeMovieFromFavouritesUseCase: RemoveMovieFromFavouritesUseCase,
    private val makeMovieFavouriteUseCase: MakeMovieFavouriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MoviesUIState())
    val state = _state.asStateFlow()

    fun getMovies(accessToken: String) {
        viewModelScope.launch(IO) {
            useCase.invoke("Bearer $accessToken").collectLatest { response ->
                when (response) {
                    is Resource.Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Resource.Success -> {
                        checkFavouriteMovies(response.data?.results ?: emptyList())
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

    private fun checkFavouriteMovies(movies: List<Movie>) {
        viewModelScope.launch(IO) {
            val favouriteMovies = getFavouriteUseCase()
            movies.forEach {
                if (favouriteMovies.contains(it.id)) {
                    it.isFavourite = true
                }
            }

            _state.update {
                it.copy(
                    isLoading = false,
                    movies = movies
                )
            }
        }
    }

    fun changeFavouriteStatus(movie: Movie, index: Int) {
        viewModelScope.launch(IO) {
            Log.i(TAG, "changeFavouriteStatus: ")
            if (movie.isFavourite) removeMovieFromFavouritesUseCase(movie.id)
            else makeMovieFavouriteUseCase(movie.id)
            movie.isFavourite = !movie.isFavourite

            val movies = _state.value.movies.toMutableList()
            movies[index] = movie

            Log.i(TAG, movies.toString())

            _state.emit(MoviesUIState(movies = movies))
            Log.i(TAG, "update")
        }
    }


    data class MoviesUIState(
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList(),
        val error: String = ""
    )
}