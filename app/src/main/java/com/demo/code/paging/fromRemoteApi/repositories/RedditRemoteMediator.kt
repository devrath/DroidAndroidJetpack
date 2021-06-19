package com.demo.code.paging.fromRemoteApi.repositories

import androidx.paging.*
import androidx.room.withTransaction
import com.demo.code.paging.fromRemoteApi.database.LocalDatabase
import com.demo.code.paging.fromRemoteApi.models.RedditKeys
import com.demo.code.paging.fromRemoteApi.models.RedditPost
import com.demo.code.paging.fromRemoteApi.networking.RedditService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RedditRemoteMediator(
    private val redditService: RedditService,
    private val LocalDatabase: LocalDatabase
) : RemoteMediator<Int, RedditPost>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RedditPost>
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
            val response = redditService.fetchPosts(
                loadSize = state.config.pageSize,
                after = loadKey?.after,
                before = loadKey?.before
            )
            val listing = response.body()?.data
            val redditPosts = listing?.children?.map { it.data }
            if (redditPosts != null) {
                LocalDatabase.withTransaction {
                    LocalDatabase.redditKeysDao()
                        .saveRedditKeys(RedditKeys(0, listing.after, listing.before))
                    LocalDatabase.redditPostsDao().savePosts(redditPosts)
                }

            }
            MediatorResult.Success(endOfPaginationReached = listing?.after == null)

        } catch (exception: IOException) {
            MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            MediatorResult.Error(exception)
        }

    }

    private suspend fun getRedditKeys(): RedditKeys? {
        return LocalDatabase.redditKeysDao().getRedditKeys().firstOrNull()

    }
}