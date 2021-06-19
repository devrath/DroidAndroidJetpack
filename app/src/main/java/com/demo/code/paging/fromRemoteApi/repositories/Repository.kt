package com.demo.code.paging.fromRemoteApi.repositories

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.demo.code.paging.fromRemoteApi.database.LocalDatabase
import com.demo.code.paging.fromRemoteApi.models.RedditPost
import com.demo.code.paging.fromRemoteApi.networking.ApiClient
import com.demo.code.paging.fromRemoteApi.networking.RedditService
import kotlinx.coroutines.flow.Flow

class Repository(context: Context) {

    // Remote API reference
    private val apiService = ApiClient.getClient().create(RedditService.service)
    // Local database reference
    private val redditDatabase = LocalDatabase.create(context)

    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(): Flow<PagingData<RedditPost>> {
        return Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = false, prefetchDistance = 1),
            remoteMediator = RedditRemoteMediator(apiService, redditDatabase),
            pagingSourceFactory = { redditDatabase.redditPostsDao().getPosts() }
        ).flow
    }
}