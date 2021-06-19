package com.demo.code.paging.fromRemoteApi.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "redditKeys")
data class RedditKeys(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val after: String?,
    val before: String?
)
