package com.demo.code.paging.fromLocalDatabase.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.code.databinding.ListItemMovieBinding
import com.demo.code.paging.fromLocalDatabase.model.Movie

class MovieListAdapter : PagedListAdapter<Movie, MovieListAdapter.MovieViewHolder>(diffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    val binding = ListItemMovieBinding
      .inflate(LayoutInflater.from(parent.context), parent, false)
    return MovieViewHolder(binding)
  }


  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    val movie: Movie? = getItem(position)
    with(holder){
      binding.title.text = movie?.title
      binding.releaseDate.text = movie?.releaseDate?.substring(0, 4)
      binding.rating.text = movie?.rating.toString()
    }
  }

  companion object {

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
      override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
          oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
          oldItem == newItem
    }
  }

  inner class MovieViewHolder(val binding: ListItemMovieBinding) :RecyclerView.ViewHolder(binding.root)
}