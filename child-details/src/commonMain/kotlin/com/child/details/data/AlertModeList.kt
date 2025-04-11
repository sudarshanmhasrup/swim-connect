package com.child.details.data

import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.mode_of_alert_option_1_label
import com.child.details.child_details.generated.resources.mode_of_alert_option_2_label
import com.child.details.child_details.generated.resources.mode_of_alert_option_3_label
import com.child.details.model.AlertMode
import com.child.details.model.SelectionOption

object AlertModeList {
    private val alertModeOptionList = listOf(
        SelectionOption(
            label = Res.string.mode_of_alert_option_1_label,
            option = AlertMode.VIBRATION
        ),
        SelectionOption(
            label = Res.string.mode_of_alert_option_2_label,
            option = AlertMode.ALARM
        ),
        SelectionOption(
            label = Res.string.mode_of_alert_option_3_label,
            option = AlertMode.BOTH
        )
    )

    fun getAlertModeOptionList(): List<SelectionOption> {
        return alertModeOptionList
    }
}