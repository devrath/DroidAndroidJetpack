package com.demo.code.liveData.mutableLiveData.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Live data is something that can be observed but can't be set
 * Mutable live data can be modified
 */
class MutableLiveDataViewModel : ViewModel() {

    private var counter : Int = 0

    private var counterLiveData : MutableLiveData<Int> = MutableLiveData()

    /**
     * Observe the live data from the Activity/Fragment
     */
    fun dataFromRepository() : LiveData<Int> = counterLiveData

    /**
     * Simulation: New value obtained from the server
     */
    fun fetchDataFromServer() {
        // Here we are updating the mutable live data
        counterLiveData.value = counter++
    }

}