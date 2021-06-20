package com.demo.code.paging.usingRemoteSource.repositories

import android.content.Context
import androidx.paging.*
import com.demo.code.paging.usingRemoteSource.database.LocalDatabase
import com.demo.code.paging.usingRemoteSource.models.FeedPost
import com.demo.code.paging.usingRemoteSource.networking.ApiClient
import com.demo.code.paging.usingRemoteSource.networking.RemoteService
import kotlinx.coroutines.flow.Flow

class Repository(context: Context) {

    // Remote API reference
    private val apiService = ApiClient.getClient().create(RemoteService.service)

    /**
     * @return Flow of Paging data
     */
    @OptIn(ExperimentalPagingApi::class)
    fun fetchPosts(): Flow<PagingData<FeedPost>> {
        return Pager(
            PagingConfig(
                pageSize = 30,
                enablePlaceholders = false,
                prefetchDistance = 1,
                initialLoadSize = 30*1,
            )
        ){
            DataSource(apiService)
        }.flow
    }

}