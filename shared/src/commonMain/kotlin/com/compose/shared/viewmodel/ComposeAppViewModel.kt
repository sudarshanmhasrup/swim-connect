package com.compose.shared.viewmodel

import androidx.lifecycle.ViewModel
import com.platform.api.util.UserPreferenceManager

class ComposeAppViewModel : ViewModel() {
    private val landingPageViewedStatusPreferenceKey = "landing-page-viewed-status"

    fun saveLandingPageAsViewed() {
        UserPreferenceManager.savePreference(
            key = landingPageViewedStatusPreferenceKey,
            value = "viewed"
        )
    }

    fun checkIfLandingPageIsViewed(): Boolean {
        val landingPageViewStatus = UserPreferenceManager.getPreference(
            key = landingPageViewedStatusPreferenceKey
        )
        return landingPageViewStatus != null
    }

    fun checkIfChildDetailsPageIsViewed(): Boolean {
        return false
    }

    fun saveChildDetailsPageAsViewed() {

    }
}