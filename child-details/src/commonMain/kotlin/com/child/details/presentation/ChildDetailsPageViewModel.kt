package com.child.details.presentation

import androidx.lifecycle.ViewModel
import com.child.details.domain.ChildConfirmDetailsUseCase
import com.child.details.domain.ChildDetails
import com.child.details.model.AlertMode
import com.child.details.model.AlertModeList
import com.child.details.model.AlertTime
import com.child.details.model.AlterTimeList
import com.child.details.model.ChildSwimmingSkillLevel
import com.child.details.model.ChildSwimmingSkillLevelOptionsList
import com.child.details.model.ConfirmChildDetailsListItem
import com.child.details.model.SelectionOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChildDetailsPageViewModel : ViewModel() {
    private var _childDetailsUiState = MutableStateFlow(ChildDetailsUiState())
    val childDetailsUiState: StateFlow<ChildDetailsUiState> = _childDetailsUiState.asStateFlow()

    fun getChildSwimmingSkillLevelOptions(): List<SelectionOption> {
        return ChildSwimmingSkillLevelOptionsList.getChildSwimmingSkillLevelOptions()
    }

    fun getCustomAlterTimeOptions(): List<SelectionOption> {
        return AlterTimeList.getAlertTimeOptions()
    }

    fun getModeOfAlertOptions(): List<SelectionOption> {
        return AlertModeList.getAlertModeOptionList()
    }

    fun getConfirmChildDetailsOptionList(): List<ConfirmChildDetailsListItem> {
        return ChildConfirmDetailsUseCase.getConfirmChildDetailsOptionList(
            childDetails = ChildDetails(
                name = _childDetailsUiState.value.childName,
                age = _childDetailsUiState.value.childAge,
                childSwimmingSkillLevel = _childDetailsUiState.value.childSwimmingSkillLevel
                    ?: ChildSwimmingSkillLevel.UNSKILLED,
                alertTime = _childDetailsUiState.value.alertTime ?: AlertTime.TWO,
                customTime = _childDetailsUiState.value.customAlertTime ?: "0",
                modeOfAlert = _childDetailsUiState.value.modeOfAlert ?: AlertMode.VIBRATION
            )
        )
    }

    fun showBackButtonContainer() {
        _childDetailsUiState.value = _childDetailsUiState.value.copy(
            showBackButtonContainer = true
        )
    }

    fun hideBackButtonContainer() {
        _childDetailsUiState.value = _childDetailsUiState.value.copy(
            showBackButtonContainer = false
        )
    }

    fun turnOnEditingMode() {
        _childDetailsUiState.value = _childDetailsUiState.value.copy(
            isEditingInformation = true
        )
    }

    fun turnOffEditingMode() {
        _childDetailsUiState.value = _childDetailsUiState.value.copy(
            isEditingInformation = false
        )
    }

    fun showConfirmationPageHeading() {
        _childDetailsUiState.value = _childDetailsUiState.value.copy(
            isConfirmationPage = true
        )
    }

    fun hideConfirmationPageHeading() {
        _childDetailsUiState.value = _childDetailsUiState.value.copy(
            isConfirmationPage = false
        )
    }

    fun updateChildName(value: String) {
        // Do not allow spaces at the start of the string.
        if (value.startsWith(" ")) {
            return
        }

        if (value.isEmpty()) {
            _childDetailsUiState.value = _childDetailsUiState.value.copy(
                childName = value,
                enableFirstContinueButton = false
            )
        } else {
            _childDetailsUiState.value = _childDetailsUiState.value.copy(
                childName = value,
                enableFirstContinueButton = true
            )
        }
    }

    fun updateChildAge(value: String) {
        val valueInNumbers = value.toIntOrNull()

        // Return from this function is user input is empty.
        if (valueInNumbers == null) {
            _childDetailsUiState.value = _childDetailsUiState.value.copy(
                childAge = "",
                enableSecondContinueButton = false
            )
            return
        }

        // Check if the value is between 0 and 17 else disable the button and show error message.
        when (value.toInt()) {
            in 0..17 -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    childAge = value,
                    showChildAgeInputError = false,
                    enableSecondContinueButton = true
                )
            }

            else -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    childAge = value,
                    showChildAgeInputError = true,
                    enableSecondContinueButton = false
                )
            }
        }
    }

    fun updateChildSwimmingSkillLevelOption(value: SelectionOption) {
        _childDetailsUiState.value = _childDetailsUiState.value.copy(
            enableThirdContinueButton = true
        )
        when (value.option) {
            ChildSwimmingSkillLevel.UNSKILLED -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    childSwimmingSkillLevel = ChildSwimmingSkillLevel.UNSKILLED
                )
            }

            ChildSwimmingSkillLevel.SEMI_SKILLED -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    childSwimmingSkillLevel = ChildSwimmingSkillLevel.SEMI_SKILLED
                )
            }

            else -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    childSwimmingSkillLevel = ChildSwimmingSkillLevel.SKILLED
                )
            }
        }
    }

    fun updateAlertTimeOption(value: SelectionOption) {
        _childDetailsUiState.value = _childDetailsUiState.value.copy(
            enableFourthContinueButton = true
        )
        when (value.option) {
            AlertTime.TWO -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    alertTime = AlertTime.TWO
                )
            }

            AlertTime.TEN -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    alertTime = AlertTime.TEN
                )
            }

            AlertTime.FIFTEEN -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    alertTime = AlertTime.FIFTEEN
                )
            }

            else -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    alertTime = AlertTime.CUSTOM
                )
            }
        }
    }

    fun updateCustomAlertTime(value: String) {
        val valueInNumbers = value.toIntOrNull()
        if (valueInNumbers == null) {
            _childDetailsUiState.value = _childDetailsUiState.value.copy(
                customAlertTime = "",
                enableFifthContinueButton = false
            )
            return
        }

        // Check if the value is between 5 and 30 else disable the button and show error message.
        if (valueInNumbers in 5..30) {
            _childDetailsUiState.value = _childDetailsUiState.value.copy(
                enableFifthContinueButton = true,
                customAlertTime = value,
                showCustomAlertTimeInputError = false
            )
        } else {
            _childDetailsUiState.value = _childDetailsUiState.value.copy(
                enableFifthContinueButton = false,
                customAlertTime = value,
                showCustomAlertTimeInputError = true
            )
        }
    }

    fun updateModeOfAlertOption(value: SelectionOption) {
        _childDetailsUiState.value = _childDetailsUiState.value.copy(
            enableSixthContinueButton = true
        )
        when (value.option) {
            AlertMode.VIBRATION -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    modeOfAlert = AlertMode.VIBRATION
                )
            }

            AlertMode.ALARM -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    modeOfAlert = AlertMode.ALARM
                )
            }

            else -> {
                _childDetailsUiState.value = _childDetailsUiState.value.copy(
                    modeOfAlert = AlertMode.BOTH
                )
            }
        }
    }

    fun saveChildDetails() {

    }
}