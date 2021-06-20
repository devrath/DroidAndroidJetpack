package com.demo.code.paging.usingLocalSource.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.demo.code.paging.usingLocalSource.bgThread
import com.demo.code.paging.usingLocalSource.db.MovieDatabase
import com.demo.code.paging.usingLocalSource.model.Movie

class MovieListViewModel(application: Application) : AndroidViewModel(application) {
  private val dao = MovieDatabase.get(application).movieDao()

  val allMovies = LivePagedListBuilder(dao.allMovies(), PagedList.Config.Builder()
      .setPageSize(PAGE_SIZE)
      .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
      .build()).build()

  fun remove(movie: Movie) = bgThread {
    dao.delete(movie)
  }

  companion object {
    private const val PAGE_SIZE = 30
    private const val ENABLE_PLACEHOLDERS = true
  }
}