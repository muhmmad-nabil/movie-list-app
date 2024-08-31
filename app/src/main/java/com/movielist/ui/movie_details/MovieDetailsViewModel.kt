package com.movielist.ui.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.movielist.domain.usecase.MakeMovieFavouriteUseCase
import com.movielist.domain.usecase.RemoveMovieFromFavouritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val makeMovieFavouriteUseCase: MakeMovieFavouriteUseCase,
    private val removeMovieFromFavouritesUseCase: RemoveMovieFromFavouritesUseCase
) : ViewModel() {
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
}