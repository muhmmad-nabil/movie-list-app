package com.muhmmad.movielist.presentation.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.muhmmad.movielist.R
import com.muhmmad.movielist.data.entity.Movie
import com.muhmmad.movielist.databinding.MovieLayoutBinding
import com.muhmmad.movielist.presentation.utils.DiffUtilCallback

class MoviesAdapter(
    private val onItemClickListener: (movie: Movie) -> Unit,
    private val onFavouriteClickListener: (movie: Movie, index: Int) -> Unit
) :
    RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private val data: MutableList<Movie> = mutableListOf()

    class ViewHolder(val binding: MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        MovieLayoutBinding.inflate(
            LayoutInflater.from(
                parent.context
            ),
            parent, false
        )
    )

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            val item = data[position]
            tvMovie.text = item.title
            tvReleaseDate.text = item.releaseDate
            tvRating.text = item.voteAverage.toString()
            ivMovie.load("https://image.tmdb.org/t/p/original" + item.posterPath) {
                crossfade(true)
                transformations(RoundedCornersTransformation(16f))
            }
            ivFavourite.setImageResource(if (item.isFavourite) R.drawable.ic_like else R.drawable.ic_unlike)

            root.setOnClickListener {
                onItemClickListener(item)
            }

            ivFavourite.setOnClickListener {
                onFavouriteClickListener(item, position)
            }
        }
    }

    fun setData(newData: List<Movie>) {
        val diffCallback = DiffUtilCallback(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }
}

private const val TAG = "MoviesAdapter"