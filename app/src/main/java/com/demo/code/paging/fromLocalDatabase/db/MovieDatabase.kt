package com.demo.code.paging.fromLocalDatabase.db

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.demo.code.paging.fromLocalDatabase.bgThread
import com.demo.code.paging.fromLocalDatabase.model.Movie
import com.demo.code.paging.fromLocalDatabase.model.MovieData
import com.google.gson.Gson
import java.io.IOException
import java.nio.charset.StandardCharsets

@Database(entities = [(Movie::class)], version = 1)
abstract class MovieDatabase : RoomDatabase() {
  abstract fun movieDao(): MovieDao

  companion object {
    private const val TAG = "MovieDatabase"
    private var instance: MovieDatabase? = null

    @Synchronized
    fun get(context: Context): MovieDatabase {
      if (instance == null) {
        instance = Room.databaseBuilder(context.applicationContext,
            MovieDatabase::class.java, "MovieDatabase")
            .addCallback(object : RoomDatabase.Callback() {
              override fun onCreate(db: SupportSQLiteDatabase) {
                fillInDatabase(context.applicationContext)
              }
            }).build()
      }
      return instance!!
    }

    private fun fillInDatabase(context: Context) {
      bgThread {
        val jsonString = readJson(context)

        if (jsonString != null) {
          val movieData = Gson().fromJson(jsonString, MovieData::class.java)
          get(context).movieDao().insert(movieData.movies)
          Log.v(TAG, movieData.toString())
        }
      }
    }

    private fun readJson(context: Context): String? {
      var json: String? = null

      try {
        val inputStream = context.assets.open("movie_data.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, StandardCharsets.UTF_8)
      } catch (e: IOException) {
        Log.e(TAG, "Error reading JSON data ::: " + e.message, e)
      }

      return json
    }
  }
}