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
abstract class LocalDatabase : RoomDatabase() {

    companion object {

        private const val  DATABASE_NAME = "redditclone.db"
        private val LOCAL_DATABASE = LocalDatabase::class.java

        fun create(context: Context): LocalDatabase {
            val databaseBuilder = Room.databaseBuilder(context, LOCAL_DATABASE, DATABASE_NAME)
            return databaseBuilder.build()
        }
    }

    abstract fun redditPostsDao(): RedditPostsDao
    abstract fun redditKeysDao(): RedditKeysDao
}