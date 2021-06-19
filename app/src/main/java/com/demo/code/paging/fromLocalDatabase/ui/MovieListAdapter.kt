package com.demo.code.paging.fromLocalDatabase.ui

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.demo.code.R
import com.demo.code.paging.fromLocalDatabase.inflate
import kotlinx.android.extensions.LayoutContainer
import com.demo.code.paging.fromLocalDatabase.model.Movie
import kotlinx.android.synthetic.main.list_item_movie.*

/**
 * @param Movie -> Type of the data per each item row
 * @param ViewHolder -> View holder patter to recycle the views
 * @param diffCallback -> We pass the diff util callback to the page list adapter so it updates the page list
 */
class MovieListAdapter : PagedListAdapter<Movie, MovieListAdapter.MovieViewHolder>(diffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    MovieViewHolder(parent.inflate(R.layout.list_item_movie))

  override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
    holder.bind(getItem(position))
  }

  companion object {

    private val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
      override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.id == newItem.id
      override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem == newItem
    }
  }

  class MovieViewHolder(override val containerView: View)
    : RecyclerView.ViewHolder(containerView), LayoutContainer {

    var movie: Movie? = null

    fun bind(movie: Movie?) {
      this.movie = movie
      title.text = movie?.title
      releaseDate.text = movie?.releaseDate?.substring(0, 4)
      rating.text = movie?.rating.toString()
    }
  }
}