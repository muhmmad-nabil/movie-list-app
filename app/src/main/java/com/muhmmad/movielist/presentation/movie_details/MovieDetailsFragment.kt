package com.muhmmad.movielist.presentation.movie_details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import coil.load
import com.muhmmad.movielist.R
import com.muhmmad.movielist.data.entity.Movie
import com.muhmmad.movielist.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(), OnClickListener {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
            arguments?.getSerializable("movie", Movie::class.java)
        else arguments?.getSerializable("movie") as Movie

        movie?.let {
            initViews(it)
        }
    }

    private fun initViews(movie: Movie) {
        binding.apply {
            ivMovie.load("https://image.tmdb.org/t/p/original" + movie.posterPath)
            tvMovie.text = movie.title
            tvReleaseDate.text = movie.releaseDate
            tvRating.text = movie.voteAverage.toString()
            tvOverview.text = movie.overview
            tvMovieInfo.text =
                getString(R.string.movie_info, movie.popularity.toString(), movie.originalLanguage)

            ivFavourite.setOnClickListener(this@MovieDetailsFragment)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.ivFavourite.setOnClickListener(null)
        _binding = null
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.ivFavourite -> {

            }
        }
    }
}