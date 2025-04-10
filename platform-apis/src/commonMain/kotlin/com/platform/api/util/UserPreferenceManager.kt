package com.platform.api.util

expect object UserPreferenceManager {

    fun savePreference(key: String, value: String)

    fun getPreference(key: String): String?
}