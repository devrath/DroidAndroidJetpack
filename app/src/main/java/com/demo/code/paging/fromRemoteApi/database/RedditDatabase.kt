package com.demo.code.paging.fromRemoteApi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.code.paging.fromRemoteApi.database.dao.RedditKeysDao
import com.demo.code.paging.fromRemoteApi.models.RedditKeys
import com.demo.code.paging.fromRemoteApi.models.RedditPost
import com.demo.code.paging.fromRemoteApi.database.dao.RedditPostsDao

@Database(
    entities = [RedditPost::class, RedditKeys::class],
    version = 1,
    exportSchema = false
)
abstract class RedditDatabase : RoomDatabase() {
    companion object {
        fun create(context: Context): RedditDatabase {
            val databaseBuilder =
                Room.databaseBuilder(context, RedditDatabase::class.java, "redditclone.db")
            return databaseBuilder.build()
        }
    }

    abstract fun redditPostsDao(): RedditPostsDao
    abstract fun redditKeysDao(): RedditKeysDao
}