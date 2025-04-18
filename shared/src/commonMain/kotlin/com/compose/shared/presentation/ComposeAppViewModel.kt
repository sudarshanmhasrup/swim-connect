package com.compose.shared.presentation

import androidx.lifecycle.ViewModel
import com.platform.api.util.UserPreferenceManager

class ComposeAppViewModel : ViewModel() {
    private val landingPageViewedStatusPreferenceKey = "landing-page-viewed-status"
    private val childDetailsPageViewedStatusPreferenceKey = "child-details-page-viewed-status"

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
        val childDetailsPageViewStatus = UserPreferenceManager.getPreference(
            key = childDetailsPageViewedStatusPreferenceKey
        )
        return childDetailsPageViewStatus != null
    }

    fun saveChildDetailsPageAsViewed() {
        UserPreferenceManager.savePreference(
            key = childDetailsPageViewedStatusPreferenceKey,
            value = "viewed"
        )
    }
}