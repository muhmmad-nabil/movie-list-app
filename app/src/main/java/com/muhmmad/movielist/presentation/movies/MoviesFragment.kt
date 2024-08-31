package com.muhmmad.movielist.presentation.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.muhmmad.movielist.R
import com.muhmmad.movielist.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

private const val TAG = "MoviesFragment"

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MoviesViewModel by viewModels()
    private val moviesAdapter = MoviesAdapter(onItemClickListener = { movie ->
        val bundle = Bundle()
        bundle.putSerializable("movie", movie)
        findNavController().navigate(R.id.action_moviesFragment_to_movieDetailsFragment, bundle)
    }, onFavouriteClickListener = { movie, index ->
        viewModel.changeFavouriteStatus(movie, index)
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
        viewModel.getMovies(getString(R.string.api_access_token))
        binding.recyclerView.adapter = moviesAdapter
    }

    private fun collectData() {
        lifecycleScope.launch {
            viewModel.state.collectLatest {
                Log.i(TAG, "collect")
                binding.loadingProgress.isVisible = it.isLoading
                if (it.movies.isNotEmpty()) {
                    moviesAdapter.setData(it.movies)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}