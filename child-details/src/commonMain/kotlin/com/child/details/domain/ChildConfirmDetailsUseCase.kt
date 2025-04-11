package com.child.details.domain

import com.child.details.data.ConfirmChildDetailsList
import com.child.details.model.AlertMode
import com.child.details.model.AlertTime
import com.child.details.model.ChildDetails
import com.child.details.model.ChildSwimmingSkillLevel
import com.child.details.model.ConfirmChildDetailsListItem
import com.child.details.model.DetailType

object ChildConfirmDetailsUseCase {
    fun getConfirmChildDetailsOptionList(
        childDetails: ChildDetails
    ): List<ConfirmChildDetailsListItem> {
        updateAllValues(childDetails = childDetails)
        return ConfirmChildDetailsList.getChildDetailsConfirmationList()
    }

    private fun updateAllValues(childDetails: ChildDetails) {
        updateNameOfTheChild(value = childDetails.name)
        updateAgeOfTheChild(value = childDetails.age)
        updateSwimmingSkillLevelOfTheChild(value = childDetails.childSwimmingSkillLevel)
        updateAlertTimeOfTheChild(
            value = childDetails.alertTime,
            customTime = childDetails.customTime
        )
        updateModeOfAlertOfTheChild(value = childDetails.modeOfAlert)
    }

    private fun updateNameOfTheChild(value: String) {
        ConfirmChildDetailsList.updateDetail(type = DetailType.NAME, value = value)
    }

    private fun updateAgeOfTheChild(value: String) {
        val ageLabel = if (value.toIntOrNull() == 1) "year" else "years"
        ConfirmChildDetailsList.updateDetail(type = DetailType.AGE, value = "$value $ageLabel")
    }

    private fun updateSwimmingSkillLevelOfTheChild(value: ChildSwimmingSkillLevel) {
        val childSwimmingSkillLevel = when (value) {
            ChildSwimmingSkillLevel.UNSKILLED -> "Unskilled"
            ChildSwimmingSkillLevel.SEMI_SKILLED -> "Semi-skilled"
            else -> "Skilled"
        }
        ConfirmChildDetailsList.updateDetail(
            type = DetailType.SKILL_LEVEL,
            value = childSwimmingSkillLevel
        )
    }

    private fun updateAlertTimeOfTheChild(value: AlertTime, customTime: String? = null) {
        val alertTime = when (value) {
            AlertTime.TWO -> "Unskilled - After 2 seconds"
            AlertTime.TEN -> "Semi-skilled - After 10 seconds"
            AlertTime.FIFTEEN -> "Skilled - After 15 seconds"
            else -> "Custom - After ${customTime ?: 0} seconds"
        }
        ConfirmChildDetailsList.updateDetail(type = DetailType.ALERT_TIME, value = alertTime)
    }

    private fun updateModeOfAlertOfTheChild(value: AlertMode) {
        val modeOfAlert = when (value) {
            AlertMode.VIBRATION -> "Vibration"
            AlertMode.ALARM -> "Alarm"
            else -> "Alarm and Vibration"
        }
        ConfirmChildDetailsList.updateDetail(type = DetailType.ALERT_MODE, value = modeOfAlert)
    }
}