package com.demo.code.dataStore.util

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


const val DATA_STORE_NAME = "user_prefs"
const val DATA_KEY = "data_key"

class DataManager(context: Context) {

    // Create the dataStore and give it a name same as shared preferences
    private val dataStore = context.createDataStore(name = DATA_STORE_NAME)

    // Create some keys we will use them to store and retrieve the data
    companion object {
        val DATA_KEY_KEY = preferencesKey<String>(DATA_KEY)
    }

    // Store user data
    suspend fun storeData(data: String) {
        dataStore.edit {
            it[DATA_KEY_KEY] = data
            // here it refers to the preferences we are editing
        }
    }

    // Retrieve the data
    val dataAsFlow: Flow<String> = dataStore.data.map {
        it[DATA_KEY_KEY] ?: ""
    }

}