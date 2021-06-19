package com.demo.code.paging.fromRemoteApi.repositories

import androidx.paging.PagingSource
import com.demo.code.paging.fromRemoteApi.models.RedditPost
import com.demo.code.paging.fromRemoteApi.networking.RedditService
import retrofit2.HttpException
import java.io.IOException

class RedditPagingSource(private val redditService: RedditService) :
    PagingSource<String, RedditPost>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
        return try {
            val response = redditService.fetchPosts(loadSize = params.loadSize)
            val listing = response.body()?.data
            val redditPosts = listing?.children?.map { it.data }
            LoadResult.Page(
                redditPosts ?: listOf(),
                listing?.before,
                listing?.after
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override val keyReuseSupported: Boolean = true
}