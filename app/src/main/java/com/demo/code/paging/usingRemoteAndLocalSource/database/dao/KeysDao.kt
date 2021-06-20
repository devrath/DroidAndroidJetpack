package com.demo.code.paging.usingRemoteAndLocalSource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.demo.code.paging.usingRemoteAndLocalSource.models.PostsKeys

@Dao
interface KeysDao {

    @Insert(onConflict = REPLACE)
    suspend fun savePostsKeys(postsKey: PostsKeys)

    @Query("SELECT * FROM postKeys ORDER BY id DESC")
    suspend fun getPostsKeys(): List<PostsKeys>

}