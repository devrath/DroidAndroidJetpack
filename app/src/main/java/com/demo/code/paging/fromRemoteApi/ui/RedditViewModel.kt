package com.demo.code.paging.fromRemoteApi.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.demo.code.paging.fromRemoteApi.models.RedditPost
import com.demo.code.paging.fromRemoteApi.repositories.RedditRepo
import kotlinx.coroutines.flow.Flow

class RedditViewModel(application: Application) : AndroidViewModel(application) {
    private val redditRepo = RedditRepo(application)

    fun fetchPosts(): Flow<PagingData<RedditPost>> {
        return redditRepo.fetchPosts().cachedIn(viewModelScope)
    }
}