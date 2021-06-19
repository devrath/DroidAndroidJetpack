package com.demo.code.paging.fromLocalDatabase.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.demo.code.paging.fromLocalDatabase.model.Movie

@Dao
interface MovieDao {
  
  @Query("SELECT * FROM Movie ORDER BY ranking")
  fun allMovies(): DataSource.Factory<Int, Movie>

  @Insert
  fun insert(movies: List<Movie>)

  @Insert
  fun insert(movie: Movie)

  @Delete
  fun delete(movie: Movie)
}