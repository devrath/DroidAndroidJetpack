package com.demo.code.paging.usingRemoteSource.repositories

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.demo.code.paging.usingRemoteSource.models.FeedPost
import com.demo.code.paging.usingRemoteSource.networking.RemoteService

@OptIn(ExperimentalPagingApi::class)
class DataSource(
    private val service: RemoteService,
) : PagingSource<Int, FeedPost>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FeedPost> {

        try {
            // Load page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = service.fetchPosts(nextPageNumber)
            val data = response.body()?.data
            val feedPosts = response.body()?.data?.children?.map { it.data }
            return if (feedPosts != null) {
                LoadResult.Page(
                    data = feedPosts,
                    prevKey = null, // Only paging forward.
                    nextKey = nextPageNumber + 1
                )
            }else{
                LoadResult.Error(Exception("data is null"))
            }
        }
         catch (e: Exception) {
            // Handle errors in this block
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, FeedPost>): Int {
        return state.hashCode()
    }
}