package com.muhmmad.movielist.presentation.movie_details

import androidx.lifecycle.ViewModel
import com.muhmmad.movielist.data.entity.Movie
import com.muhmmad.movielist.domain.use_case.GetFavouriteUseCase
import com.muhmmad.movielist.presentation.movies.MoviesViewModel.MoviesUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val useCase: GetFavouriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MoviesUIState())
    val state = _state.asSharedFlow()

    fun makeMovieFavourite() {

    }


    data class MovieDetailsUIState(
        val isLoading: Boolean = false,
        val error: String = ""
    )
}