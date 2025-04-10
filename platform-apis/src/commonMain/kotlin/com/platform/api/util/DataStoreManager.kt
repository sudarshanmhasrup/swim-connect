package com.platform.api.util

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

expect object DataStoreManager {

    fun getDataStoreInstance(): DataStore<Preferences>?
}