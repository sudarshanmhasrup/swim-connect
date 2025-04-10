package com.platform.api.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object UserPreferenceManager {
    private var preferences: DataStore<Preferences>? = DataStoreManager.getDataStoreInstance()

    private fun initialize() {
        preferences = DataStoreManager.getDataStoreInstance()
    }

    fun savePreference(key: String, value: String) {
        val dataStore = preferences ?: return
        val preferenceKey = stringPreferencesKey(name = key)
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit {
                it[preferenceKey] = value
            }
        }
    }

    fun getPreference(key: String): String? {
        val dataStore = preferences ?: return null
        val preferenceKey = stringPreferencesKey(name = key)
        return runBlocking {
            dataStore.data
                .map { it[preferenceKey] }
                .firstOrNull()
        }
    }
}