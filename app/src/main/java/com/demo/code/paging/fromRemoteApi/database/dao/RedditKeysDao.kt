package com.demo.code.paging.fromRemoteApi.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.demo.code.paging.fromRemoteApi.models.RedditKeys

@Dao
interface RedditKeysDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveRedditKeys(redditKey: RedditKeys)

    @Query("SELECT * FROM redditKeys ORDER BY id DESC")
    suspend fun getRedditKeys(): List<RedditKeys>

}