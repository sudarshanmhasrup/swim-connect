package com.child.details.presentation

import com.child.details.model.AlertMode
import com.child.details.model.AlertTime
import com.child.details.model.ChildSwimmingSkillLevel

data class ChildDetailsUiState(
    val childName: String = "",
    val enableFirstContinueButton: Boolean = false,
    val childAge: String = "",
    val showChildAgeInputError: Boolean = false,
    val enableSecondContinueButton: Boolean = false,
    val childSwimmingSkillLevel: ChildSwimmingSkillLevel? = null,
    val enableThirdContinueButton: Boolean = false,
    val alertTime: AlertTime? = null,
    val enableFourthContinueButton: Boolean = false,
    val customAlertTime: String? = null,
    val showCustomAlertTimeInputError: Boolean = false,
    val enableFifthContinueButton: Boolean = false,
    val modeOfAlert: AlertMode? = null,
    val enableSixthContinueButton: Boolean = false,
    val isConfirmationPage: Boolean = false,
    val isEditingInformation: Boolean = false,
    val showBackButtonContainer: Boolean = true
)