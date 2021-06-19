package com.demo.code.paging.fromRemoteApi.repositories

import androidx.paging.*
import androidx.room.withTransaction
import com.demo.code.paging.fromRemoteApi.database.LocalDatabase
import com.demo.code.paging.fromRemoteApi.models.PostsKeys
import com.demo.code.paging.fromRemoteApi.models.FeedPost
import com.demo.code.paging.fromRemoteApi.networking.RemoteService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class DataMediator(
    private val remoteService: RemoteService,
    private val LocalDatabase: LocalDatabase
) : RemoteMediator<Int, FeedPost>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, FeedPost>
    ): MediatorResult {

        return try {

            val loadKey = when(loadType){
                LoadType.REFRESH -> null
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND ->{
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    getRedditKeys()
                }
            }

            val response = remoteService.fetchPosts(
                loadSize = state.config.pageSize,
                after = loadKey?.after,
                before = loadKey?.before
            )

            val listing = response.body()?.data

            val feedPosts = listing?.children?.map { it.data }

            if (feedPosts != null) {
                LocalDatabase.withTransaction {
                    LocalDatabase.keysDao()
                        .savePostsKeys(PostsKeys(0, listing.after, listing.before))
                    LocalDatabase.postsDao().savePosts(feedPosts)
                }

            }
            MediatorResult.Success(endOfPaginationReached = listing?.after == null)

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }

    }

    private suspend fun getRedditKeys(): PostsKeys? {
        return LocalDatabase.keysDao().getPostsKeys().firstOrNull()
    }
}