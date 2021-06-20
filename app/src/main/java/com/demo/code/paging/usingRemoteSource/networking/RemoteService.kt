package com.demo.code.paging.usingRemoteSource.networking

import com.demo.code.paging.usingRemoteSource.models.PostsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    companion object {
        val service = RemoteService::class.java
    }

    @GET("/r/aww/hot.json")
    suspend fun fetchPosts(
        @Query("limit") loadSize: Int = 0,
        @Query("after") after: String? = null,
        @Query("before") before: String? = null
    ): Response<PostsApiResponse>


}