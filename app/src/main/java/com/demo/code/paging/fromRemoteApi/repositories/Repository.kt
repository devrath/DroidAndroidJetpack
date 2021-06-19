package com.demo.code.paging.fromRemoteApi.repositories

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.demo.code.paging.fromRemoteApi.database.LocalDatabase
import com.demo.code.paging.fromRemoteApi.models.FeedPost
import com.demo.code.paging.fromRemoteApi.networking.ApiClient
import com.demo.code.paging.fromRemoteApi.networking.RemoteService
import kotlinx.coroutines.flow.Flow

class Repository(context: Context) {

    // Remote API reference
    private val apiService = ApiClient.getClient().create(RemoteService.service)
    // Local database reference
    private val localDatabase = LocalDatabase.create(context)

    /**
     * @return Flow of Paging data
     */
    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(): Flow<PagingData<FeedPost>> {

        return Pager(
            PagingConfig(pageSize = 10, enablePlaceholders = false, prefetchDistance = 1),
            remoteMediator = DataMediator(apiService, localDatabase),
            pagingSourceFactory = { localDatabase.postsDao().getPosts() }
        ).flow

    }

}