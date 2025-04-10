package com.platform.api.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.platform.api.createDataStore

actual object DataStoreManager {
    private var preferences: DataStore<Preferences>? = null

    fun initialize(context: Context) {
        preferences = createDataStore(context = context)
    }

    actual fun getDataStoreInstance(): DataStore<Preferences>? {
        return preferences
    }
}