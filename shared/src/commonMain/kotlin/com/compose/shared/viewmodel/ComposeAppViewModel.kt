package com.compose.shared.viewmodel

import androidx.lifecycle.ViewModel

class ComposeAppViewModel: ViewModel() {

    fun checkIfLandingPageIsViewed(): Boolean {
        return true
    }

    fun saveLandingPageAsViewed() {

    }

    fun checkIfChildDetailsPageIsViewed(): Boolean {
        return false
    }

    fun saveChildDetailsPageAsViewed() {

    }
}