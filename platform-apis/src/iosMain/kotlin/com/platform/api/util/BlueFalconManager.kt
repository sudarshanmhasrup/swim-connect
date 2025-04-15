package com.platform.api.util

import dev.bluefalcon.ApplicationContext
import dev.bluefalcon.BlueFalcon
import platform.UIKit.UIApplication

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object BlueFalconManager {

    private var bleFalcon: BlueFalcon? = null

    fun initialize() {
        bleFalcon = BlueFalcon(
            log = null,
            context = UIApplication.sharedApplication as ApplicationContext
        )
    }

    actual fun getBlueFalcon(): BlueFalcon {
        return bleFalcon!!
    }
}