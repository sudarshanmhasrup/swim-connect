package com.platform.api.util

import android.app.Application
import dev.bluefalcon.BlueFalcon

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object BlueFalconManager {

    private var bleFalcon: BlueFalcon? = null

    fun initialize(applicationContext: Application) {
        bleFalcon = BlueFalcon(context = applicationContext)
    }

    actual fun getBlueFalcon(): BlueFalcon {
        return bleFalcon!!
    }
}