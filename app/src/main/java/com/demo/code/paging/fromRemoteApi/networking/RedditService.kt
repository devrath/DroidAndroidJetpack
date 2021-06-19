package com.demo.code.paging.fromRemoteApi.networking

import com.demo.code.paging.fromRemoteApi.models.RedditApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    companion object {
        val service = RedditService::class.java
    }

    @GET("/r/aww/hot.json")
    suspend fun fetchPosts(
        @Query("limit") loadSize: Int = 0,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response<RedditApiResponse>


}