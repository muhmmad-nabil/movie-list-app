package com.muhmmad.movielist.presentation.movies

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.muhmmad.movielist.data.entity.Movie
import com.muhmmad.movielist.databinding.MovieLayoutBinding

class MoviesAdapter(private val onItemClickListener: (movie: Movie) -> Unit) :
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

            root.setOnClickListener {
                onItemClickListener(item)
            }
        }
    }

    fun addItems(movies: List<Movie>) {
        data.clear()
        data.addAll(movies)
        notifyDataSetChanged()
    }
}