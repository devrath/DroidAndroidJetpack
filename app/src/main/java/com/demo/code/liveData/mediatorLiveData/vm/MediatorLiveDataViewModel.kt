package com.demo.code.liveData.mediatorLiveData.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MediatorLiveDataViewModel : ViewModel() {

    private var counter : Int = 0

    private var counterFromServerLiveData : MutableLiveData<Int> = MutableLiveData()
    private var counterFromLocalDbLiveData : MutableLiveData<Int> = MutableLiveData()

    fun observeData(): LiveData<Int> {

       val dataSourceLiveData : MediatorLiveData<Int> = MediatorLiveData()

        dataSourceLiveData.addSource(counterFromServerLiveData) {
            dataSourceLiveData.value = it
        }
        dataSourceLiveData.addSource(counterFromLocalDbLiveData) {
            dataSourceLiveData.value = it
        }

        return dataSourceLiveData
    }

    fun fetchDataFromServer() {
        counterFromServerLiveData.value = counter++
    }

    fun fetchDataFromLocalDb() {
        counterFromLocalDbLiveData.value = counter++
    }


}