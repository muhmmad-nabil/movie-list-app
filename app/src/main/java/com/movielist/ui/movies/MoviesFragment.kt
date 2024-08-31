package com.movielist.ui.movies

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.movielist.databinding.FragmentMoviesBinding
import com.movielist.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()
    private val moviesAdapter = MoviesAdapter(onItemClickListener = { movie ->
        //navigate to movie details fragment with movie object as argument
        val bundle = Bundle()
        bundle.putSerializable("movie", movie)
        findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment, bundle)
    }, onFavouriteClickListener = { movie, index, adapter ->
        if (movie.isFavourite) viewModel.removeMovieFromFavourites(movie.id)
        else viewModel.makeMovieFavourite(movie.id)
        movie.isFavourite = !movie.isFavourite
        adapter.changeItem(index, movie)
    })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collectData()

        if (isInternetAvailable()) viewModel.getMovies()
        else binding.ivNoInternet.isVisible = true

        binding.recyclerView.adapter = moviesAdapter
    }

    private fun isInternetAvailable(): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo ?: return false
            return networkInfo.isConnected
        }
    }

    private fun collectData() {
        //collect data from viewModel
        lifecycleScope.launch {
            viewModel.state.collectLatest {
                binding.loadingProgress.isVisible = it.isLoading
                if (it.movies.isNotEmpty()) moviesAdapter.setData(it.movies)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}