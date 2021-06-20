package com.demo.code.paging.usingRemoteSource.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.demo.code.paging.usingRemoteSource.models.FeedPost

@Dao
interface PostsDao {

    @Insert(onConflict = REPLACE)
    suspend fun savePosts(feedPosts: List<FeedPost>)

    @Query("SELECT * FROM feedPosts")
    fun getPosts(): PagingSource<Int, FeedPost>

}