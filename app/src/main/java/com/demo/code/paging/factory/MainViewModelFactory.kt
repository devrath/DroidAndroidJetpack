package com.demo.code.paging.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.demo.code.paging.horizontalPaging.vm.HorizontalPagingViewModel
import com.demo.code.paging.network.APIService

class MainViewModelFactory(private val apiService: APIService) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HorizontalPagingViewModel::class.java)) {
            return HorizontalPagingViewModel(apiService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}