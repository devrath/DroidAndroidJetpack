package com.demo.code.paging.fromRemoteApi.repositories

import androidx.paging.*
import androidx.room.withTransaction
import com.demo.code.paging.fromRemoteApi.database.RedditDatabase
import com.demo.code.paging.fromRemoteApi.models.RedditKeys
import com.demo.code.paging.fromRemoteApi.models.RedditPost
import com.demo.code.paging.fromRemoteApi.networking.RedditService
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class RedditRemoteMediator(
    private val redditService: RedditService,
    private val redditDatabase: RedditDatabase
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
                redditDatabase.withTransaction {
                    redditDatabase.redditKeysDao()
                        .saveRedditKeys(RedditKeys(0, listing.after, listing.before))
                    redditDatabase.redditPostsDao().savePosts(redditPosts)
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
        return redditDatabase.redditKeysDao().getRedditKeys().firstOrNull()

    }
}