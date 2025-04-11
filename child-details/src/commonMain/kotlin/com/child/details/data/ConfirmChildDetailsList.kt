package com.child.details.data

import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.alert_time_input_placeholder
import com.child.details.child_details.generated.resources.child_age_input_placeholder
import com.child.details.child_details.generated.resources.child_name_input_placeholder
import com.child.details.child_details.generated.resources.child_swimming_skill_level_input_placeholder
import com.child.details.child_details.generated.resources.mode_of_alert_input_placeholder
import com.child.details.model.ConfirmChildDetailsListItem
import com.child.details.model.DetailType
import com.child.details.presentation.navigation.ChildDetailsPageRoutes

object ConfirmChildDetailsList {
    private val childDetailsConfirmationList = mutableListOf(
        ConfirmChildDetailsListItem(
            label = Res.string.child_name_input_placeholder,
            value = "John",
            route = ChildDetailsPageRoutes.CHILD_NAME_INPUT_UI
        ),
        ConfirmChildDetailsListItem(
            label = Res.string.child_age_input_placeholder,
            value = "20 years",
            route = ChildDetailsPageRoutes.CHILD_AGE_INPUT_UI
        ),
        ConfirmChildDetailsListItem(
            label = Res.string.child_swimming_skill_level_input_placeholder,
            value = "Beginner",
            route = ChildDetailsPageRoutes.CHILD_SWIMMING_SKILL_LEVEL_UI
        ),
        ConfirmChildDetailsListItem(
            label = Res.string.alert_time_input_placeholder,
            value = "Custom - After 35 seconds.",
            route = ChildDetailsPageRoutes.ALERT_TIME_INPUT_UI
        ),
        ConfirmChildDetailsListItem(
            label = Res.string.mode_of_alert_input_placeholder,
            value = "Vibration",
            route = ChildDetailsPageRoutes.MODE_OF_ALERT_INPUT_UI
        )
    )

    fun getChildDetailsConfirmationList(): List<ConfirmChildDetailsListItem> {
        return childDetailsConfirmationList
    }

    fun updateDetail(type: DetailType, value: String) {
        val index = when (type) {
            DetailType.NAME -> 0
            DetailType.AGE -> 1
            DetailType.SKILL_LEVEL -> 2
            DetailType.ALERT_TIME -> 3
            DetailType.ALERT_MODE -> 4
        }
        childDetailsConfirmationList[index] = childDetailsConfirmationList[index].copy(
            value = value
        )
    }
}