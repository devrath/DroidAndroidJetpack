package com.demo.code.paging.usingRemoteAndLocalSource.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.code.paging.usingRemoteAndLocalSource.database.dao.KeysDao
import com.demo.code.paging.usingRemoteAndLocalSource.models.PostsKeys
import com.demo.code.paging.usingRemoteAndLocalSource.models.FeedPost
import com.demo.code.paging.usingRemoteAndLocalSource.database.dao.PostsDao

@Database(
    entities = [FeedPost::class, PostsKeys::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {

    companion object {

        private const val  DATABASE_NAME = "paging_demo.db"
        private val LOCAL_DATABASE = LocalDatabase::class.java

        fun create(context: Context): LocalDatabase {
            val databaseBuilder = Room.databaseBuilder(context, LOCAL_DATABASE, DATABASE_NAME)
            return databaseBuilder.build()
        }
    }

    abstract fun postsDao(): PostsDao
    abstract fun keysDao(): KeysDao
}