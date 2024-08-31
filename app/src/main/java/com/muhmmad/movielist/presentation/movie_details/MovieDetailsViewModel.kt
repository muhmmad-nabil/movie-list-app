package com.muhmmad.movielist.presentation.movie_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muhmmad.movielist.data.entity.Movie
import com.muhmmad.movielist.domain.use_case.GetFavouriteUseCase
import com.muhmmad.movielist.domain.use_case.MakeMovieFavouriteUseCase
import com.muhmmad.movielist.domain.use_case.RemoveMovieFromFavouritesUseCase
import com.muhmmad.movielist.presentation.movies.MoviesViewModel.MoviesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "MovieDetailsViewModel"

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val makeMovieFavouriteUseCase: MakeMovieFavouriteUseCase,
    private val removeMovieFromFavouritesUseCase: RemoveMovieFromFavouritesUseCase
) : ViewModel() {
    fun makeMovieFavourite(id: Int) {
        viewModelScope.launch(IO) {
            Log.i(TAG, "Movie is Favourite : $id")
            makeMovieFavouriteUseCase(id)
        }
    }

    fun removeMovieFromFavourites(id: Int) {
        viewModelScope.launch(IO) {
            Log.i(TAG, "remove movie from Favourite : $id")
            removeMovieFromFavouritesUseCase(id)
        }
    }
}