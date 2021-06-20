package com.demo.code.paging.usingRemoteSource.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.demo.code.paging.usingRemoteSource.models.FeedPost
import com.demo.code.paging.usingRemoteSource.repositories.Repository
import kotlinx.coroutines.flow.Flow

class RemoteApiViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        val thisClass = RemoteApiViewModel::class.java
    }

    private val redditRepo = Repository(application)

    fun fetchPosts(): Flow<PagingData<FeedPost>> {
        return redditRepo.fetchPosts().cachedIn(viewModelScope)
    }
}