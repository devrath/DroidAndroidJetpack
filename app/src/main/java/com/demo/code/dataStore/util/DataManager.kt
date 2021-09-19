package com.demo.code.dataStore.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


const val DATA_STORE_NAME = "user_prefs"
const val DATA_KEY = "data_key"

class DataManager(val context: Context) {

    // Create the dataStore and give it a name same as shared preferences
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
    
    // Create some keys we will use them to store and retrieve the data
    companion object {
        val DATA_KEY_KEY = stringPreferencesKey(DATA_KEY)
    }

    // Store user data
    suspend fun storeData(data: String) {
        context.dataStore.edit { it[DATA_KEY_KEY] = data }
    }

    // Retrieve the data
    val dataAsFlow: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[DATA_KEY_KEY] ?: "" }

}