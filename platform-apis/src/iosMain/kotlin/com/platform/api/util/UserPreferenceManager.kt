package com.platform.api.util

actual object UserPreferenceManager {

    actual fun savePreference(key: String, value: String) {

    }

    actual fun getPreference(key: String): String? {
        return null
    }
}