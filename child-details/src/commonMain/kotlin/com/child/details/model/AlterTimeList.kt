package com.child.details.model

import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.alert_time_option_1_label
import com.child.details.child_details.generated.resources.alert_time_option_2_label
import com.child.details.child_details.generated.resources.alert_time_option_3_label
import com.child.details.child_details.generated.resources.alert_time_option_4_label

object AlterTimeList {
    private val alertTimeOptionList = listOf(
        SelectionOption(
            label = Res.string.alert_time_option_1_label,
            option = AlertTime.TWO
        ),
        SelectionOption(
            label = Res.string.alert_time_option_2_label,
            option = AlertTime.TEN
        ),
        SelectionOption(
            label = Res.string.alert_time_option_3_label,
            option = AlertTime.FIFTEEN
        ),
        SelectionOption(
            label = Res.string.alert_time_option_4_label,
            option = AlertTime.CUSTOM,
        )
    )

    fun getAlertTimeOptions(): List<SelectionOption> {
        return alertTimeOptionList
    }
}