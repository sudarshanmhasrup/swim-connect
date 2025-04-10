package com.platform.api.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.platform.api.createDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object UserPreferenceManager {
    private var preferences: DataStore<Preferences>? = null

    fun initialize(context: Context) {
        preferences = createDataStore(context = context)
    }

    actual fun savePreference(key: String, value: String) {
        val dataStore = preferences ?: return
        val preferenceKey = stringPreferencesKey(name = key)
        CoroutineScope(Dispatchers.IO).launch {
            dataStore.edit {
                it[preferenceKey] = value
            }
        }
    }

    actual fun getPreference(key: String): String? {
        val dataStore = preferences ?: return null
        val preferenceKey = stringPreferencesKey(name = key)
        return runBlocking {
            dataStore.data
                .map { it[preferenceKey] }
                .firstOrNull()
        }
    }
}