package com.demo.code.paging.fromRemoteApi.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.demo.code.paging.fromRemoteApi.models.FeedPost
import com.demo.code.paging.fromRemoteApi.repositories.Repository
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