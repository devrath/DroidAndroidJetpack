package com.demo.code.paging.horizontalPaging.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.demo.code.paging.network.APIService
import com.demo.code.paging.source.PagingSource

class HorizontalPagingViewModel (private val apiService: APIService) : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 6)) {
        PagingSource(apiService)
    }.flow.cachedIn(viewModelScope)
}