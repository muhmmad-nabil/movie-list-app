package com.movielist

import com.movielist.data.entity.Movie
import com.movielist.data.entity.MoviesResponse
import com.movielist.data.local.LocalDataSource
import com.movielist.data.remote.RetrofitDataSource
import com.movielist.data.repo.MoviesRepoImpl
import com.movielist.domain.entity.Movies
import com.movielist.domain.usecase.GetFavouriteUseCase
import com.movielist.domain.usecase.GetMoviesUseCase
import com.movielist.domain.usecase.MakeMovieFavouriteUseCase
import com.movielist.domain.usecase.RemoveMovieFromFavouritesUseCase
import com.movielist.ui.movies.MoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import org.junit.Test

@ExperimentalCoroutinesApi
class MoviesViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private val scope = TestScope(dispatcher)
    private val data = Movies(
        listOf(
            com.movielist.domain.entity.Movie(
                1, "en", "fdfdfffd", 15.2, "fdfdf",
                "15/2/2024", "fdfdf", 15.2, true
            ),
            com.movielist.domain.entity.Movie(
                2, "en", "fdfdfffd", 15.2, "fdfdf",
                "15/2/2024", "fdfdf", 15.2, true
            ),
            com.movielist.domain.entity.Movie(
                3, "en", "fdfdfffd", 15.2, "fdfdf",
                "15/2/2024", "fdfdf", 15.2, true
            ),
            com.movielist.domain.entity.Movie(
                4, "en", "fdfdfffd", 15.2, "fdfdf",
                "15/2/2024", "fdfdf", 15.2, false
            ),
            com.movielist.domain.entity.Movie(
                5, "en", "fdfdfffd", 15.2, "fdfdf",
                "15/2/2024", "fdfdf", 15.2, false
            )
        )
    )

    @Test
    fun loadingState_isSetCorrectly() {
        val viewModel = getViewModel()
        viewModel.getMovies()
        scope.launch {
            viewModel.state.collectLatest {
                assert(it.isLoading)
            }
        }
    }

    @Test
    fun dataArrivedSuccessfully() {
        val viewModel = getViewModel()
        viewModel.getMovies()
        scope.launch {
            viewModel.state.collectLatest {
                assert(it.movies == data.results)
            }
        }
    }

    @Test
    fun favouriteMoviesAreSetCorrectly() {
        val viewModel = getViewModel()
        viewModel.getMovies()
        scope.launch {
            viewModel.state.collectLatest {
                assert(it.movies[0].isFavourite)
            }
        }
    }

    private fun getViewModel(): MoviesViewModel {
        val repo = MoviesRepoImpl(TestRetrofitDataSource(), TestLocalDataSource())
        val getMoviesUseCase = GetMoviesUseCase(repo)
        val getFavouriteUseCase = GetFavouriteUseCase(repo)
        val removeMovieFromFavouritesUseCase = RemoveMovieFromFavouritesUseCase(repo)
        val makeMovieFavouriteUseCase = MakeMovieFavouriteUseCase(repo)
        return MoviesViewModel(
            getMoviesUseCase,
            getFavouriteUseCase,
            removeMovieFromFavouritesUseCase,
            makeMovieFavouriteUseCase
        )
    }

    class TestRetrofitDataSource : RetrofitDataSource {
        override suspend fun getMovies(accessToken: String): retrofit2.Response<MoviesResponse> {
            return retrofit2.Response.success(
                MoviesResponse(
                    page = 1,
                    results = listOf(
                        Movie(
                            false,
                            "fdfdf",
                            listOf(1, 2, 3),
                            1,
                            "en",
                            "Test Movie",
                            "fdfdfffd",
                            15.2,
                            "fdfdf",
                            "15/2/2024",
                            "fdfdf",
                            false,
                            15.2,
                            1
                        ),
                        Movie(
                            false,
                            "fdfdf",
                            listOf(1, 2, 3),
                            2,
                            "en",
                            "Test Movie",
                            "fdfdfffd",
                            15.2,
                            "fdfdf",
                            "15/2/2024",
                            "fdfdf",
                            false,
                            15.2,
                            1
                        ),
                        Movie(
                            false,
                            "fdfdf",
                            listOf(1, 2, 3),
                            3,
                            "en",
                            "Test Movie",
                            "fdfdfffd",
                            15.2,
                            "fdfdf",
                            "15/2/2024",
                            "fdfdf",
                            false,
                            15.2,
                            1
                        ),
                        Movie(
                            false,
                            "fdfdf",
                            listOf(1, 2, 3),
                            4,
                            "en",
                            "Test Movie",
                            "fdfdfffd",
                            15.2,
                            "fdfdf",
                            "15/2/2024",
                            "fdfdf",
                            false,
                            15.2,
                            1
                        ),
                        Movie(
                            false,
                            "fdfdf",
                            listOf(1, 2, 3),
                            5,
                            "en",
                            "Test Movie",
                            "fdfdfffd",
                            15.2,
                            "fdfdf",
                            "15/2/2024",
                            "fdfdf",
                            false,
                            15.2,
                            1
                        ),
                    ),
                    totalPages = 1,
                    totalResults = 5
                )
            )
        }

    }

    class TestLocalDataSource : LocalDataSource {
        override suspend fun getFavouriteMovies(): List<Int> = listOf(1, 2, 3)

        override suspend fun makeMovieFavourite(id: Int) {

        }

        override suspend fun removeMovieFromFavourites(id: Int) {

        }
    }
}