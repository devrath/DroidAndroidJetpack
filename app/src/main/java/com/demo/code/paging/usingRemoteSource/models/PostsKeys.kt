package com.demo.code.paging.usingRemoteSource.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "postKeys")
data class PostsKeys(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val after: String?,
    val before: String?
)
