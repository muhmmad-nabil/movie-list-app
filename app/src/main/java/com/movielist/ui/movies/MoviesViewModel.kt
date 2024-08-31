package com.movielist.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movielist.domain.entity.Movie
import com.movielist.domain.usecase.GetFavouriteUseCase
import com.movielist.domain.usecase.GetMoviesUseCase
import com.movielist.domain.usecase.MakeMovieFavouriteUseCase
import com.movielist.domain.usecase.RemoveMovieFromFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.movielist.domain.entity.Resource.Loading
import com.movielist.domain.entity.Resource.Success
import com.movielist.domain.entity.Resource.Error

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getFavouriteUseCase: GetFavouriteUseCase,
    private val removeMovieFromFavouritesUseCase: RemoveMovieFromFavouritesUseCase,
    private val makeMovieFavouriteUseCase: MakeMovieFavouriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MoviesUIState())
    val state = _state.asSharedFlow()

    fun getMovies() {
        viewModelScope.launch(IO) {
            getMoviesUseCase.invoke().let { response ->
                when (response) {
                    is Loading -> {
                        _state.update { it.copy(isLoading = true) }
                    }

                    is Success -> {
                        checkFavouriteMovies(response.data?.results ?: emptyList())
                    }

                    is Error -> {
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

    fun makeMovieFavourite(id: Int) {
        viewModelScope.launch(IO) {
            makeMovieFavouriteUseCase(id)
        }
    }

    fun removeMovieFromFavourites(id: Int) {
        viewModelScope.launch(IO) {
            removeMovieFromFavouritesUseCase(id)
        }
    }


    data class MoviesUIState(
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList(),
        val error: String = ""
    )
}