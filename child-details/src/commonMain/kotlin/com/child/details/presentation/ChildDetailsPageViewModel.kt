package com.child.details.presentation

import androidx.lifecycle.ViewModel
import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.alert_time_option_1_label
import com.child.details.child_details.generated.resources.alert_time_option_2_label
import com.child.details.child_details.generated.resources.alert_time_option_3_label
import com.child.details.child_details.generated.resources.alert_time_option_4_label
import com.child.details.child_details.generated.resources.child_swimming_skill_level_option_1_label
import com.child.details.child_details.generated.resources.child_swimming_skill_level_option_2_label
import com.child.details.child_details.generated.resources.child_swimming_skill_level_option_3_label
import com.child.details.child_details.generated.resources.mode_of_alert_option_1_label
import com.child.details.child_details.generated.resources.mode_of_alert_option_2_label
import com.child.details.child_details.generated.resources.mode_of_alert_option_3_label
import com.child.details.data.AlertModeList
import com.child.details.data.AlterTimeList
import com.child.details.data.ChildSwimmingSkillLevelOptionsList
import com.child.details.domain.ChildConfirmDetailsUseCase
import com.child.details.model.AlertMode
import com.child.details.model.AlertTime
import com.child.details.model.ChildDetails
import com.child.details.model.ChildSwimmingSkillLevel
import com.child.details.model.ConfirmChildDetailsListItem
import com.child.details.model.SelectionOption
import com.platform.api.util.UserPreferenceManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChildDetailsPageViewModel : ViewModel() {
    private var _childDetailsUiState = MutableStateFlow(ChildDetailsUiState())
    val childDetailsUiState: StateFlow<ChildDetailsUiState> = _childDetailsUiState.asStateFlow()

    private val childNamePreferenceKey = "child-name"
    private val childAgePreferenceKey = "child-age"
    private val childSwimmingSkillLevelPreferenceKey = "child-swimming-skill-level"
    private val alertTimePreferenceKey = "alert-time-preference-key"
    private val customAlertTimePreferenceKey = "custom-alert-time-preference-key"
    private val modeOfAlertPreferenceKey = "mode-of-alert-preference-key"

    init {
        getChildName()
        getChildAge()
        getChildSwimmingSkillLevel()
        getAlertTime()
        getCustomAlertTime()
        getModeOfAlert()
    }

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

    fun saveChildName() {
        UserPreferenceManager.savePreference(
            key = childNamePreferenceKey,
            value = _childDetailsUiState.value.childName
        )
    }

    private fun getChildName() {
        val childName = UserPreferenceManager.getPreference(childNamePreferenceKey) ?: return
        _childDetailsUiState.value = _childDetailsUiState.value.copy(childName = childName)
        updateChildName(value = childName)
    }

    fun saveChildAge() {
        UserPreferenceManager.savePreference(
            key = childAgePreferenceKey,
            value = _childDetailsUiState.value.childAge
        )
    }

    private fun getChildAge() {
        val childAge = UserPreferenceManager.getPreference(childAgePreferenceKey) ?: return
        _childDetailsUiState.value = _childDetailsUiState.value.copy(childAge = childAge)
        updateChildAge(value = childAge)
    }

    fun saveChildSwimmingSkillLevel() {
        var childSwimmingSkillLevel = when (_childDetailsUiState.value.childSwimmingSkillLevel) {
            ChildSwimmingSkillLevel.UNSKILLED -> "Unskilled"
            ChildSwimmingSkillLevel.SEMI_SKILLED -> "Semi-skilled"
            else -> "Skilled"
        }
        UserPreferenceManager.savePreference(
            key = childSwimmingSkillLevelPreferenceKey,
            value = childSwimmingSkillLevel
        )
    }

    private fun getChildSwimmingSkillLevel() {
        val childSwimmingSkillLevel =
            UserPreferenceManager.getPreference(childSwimmingSkillLevelPreferenceKey) ?: return
        val option = when (childSwimmingSkillLevel) {
            "Unskilled" -> ChildSwimmingSkillLevel.UNSKILLED
            "Semi-skilled" -> ChildSwimmingSkillLevel.SEMI_SKILLED
            else -> ChildSwimmingSkillLevel.SKILLED
        }
        val label = when (option) {
            ChildSwimmingSkillLevel.UNSKILLED -> Res.string.child_swimming_skill_level_option_1_label
            ChildSwimmingSkillLevel.SEMI_SKILLED -> Res.string.child_swimming_skill_level_option_2_label
            else -> Res.string.child_swimming_skill_level_option_3_label
        }
        val selectionOption = SelectionOption(
            label = label,
            option = option
        )
        updateChildSwimmingSkillLevelOption(value = selectionOption)
    }

    fun saveAlertTime() {
        var alertTime = when (_childDetailsUiState.value.alertTime) {
            AlertTime.TWO -> "Two"
            AlertTime.TEN -> "Ten"
            AlertTime.FIFTEEN -> "Fifteen"
            else -> "Custom"
        }
        UserPreferenceManager.savePreference(
            key = alertTimePreferenceKey,
            value = alertTime
        )
    }

    private fun getAlertTime() {
        val alertTime =
            UserPreferenceManager.getPreference(alertTimePreferenceKey) ?: return
        val option = when (alertTime) {
            "Two" -> AlertTime.TWO
            "Ten" -> AlertTime.TEN
            "Fifteen" -> AlertTime.FIFTEEN
            else -> AlertTime.CUSTOM
        }
        val label = when (option) {
            AlertTime.TWO -> Res.string.alert_time_option_1_label
            AlertTime.TEN -> Res.string.alert_time_option_2_label
            AlertTime.FIFTEEN -> Res.string.alert_time_option_3_label
            else -> Res.string.alert_time_option_4_label
        }
        val selectionOption = SelectionOption(
            label = label,
            option = option
        )
        updateAlertTimeOption(value = selectionOption)
    }

    fun saveCustomAlertTime() {
        UserPreferenceManager.savePreference(
            key = customAlertTimePreferenceKey,
            value = _childDetailsUiState.value.customAlertTime ?: ""
        )
    }

    fun getCustomAlertTime() {
        val customAlertTime =
            UserPreferenceManager.getPreference(customAlertTimePreferenceKey) ?: return
        updateCustomAlertTime(value = customAlertTime)
    }

    fun saveModeOfAlert() {
        var modeOfAlert = when (_childDetailsUiState.value.modeOfAlert) {
            AlertMode.ALARM -> "Alarm"
            AlertMode.VIBRATION -> "Vibration"
            else -> "Both"
        }
        UserPreferenceManager.savePreference(
            key = modeOfAlertPreferenceKey,
            value = modeOfAlert
        )
    }

    private fun getModeOfAlert() {
        val modeOfAlert =
            UserPreferenceManager.getPreference(modeOfAlertPreferenceKey) ?: return
        val option = when (modeOfAlert) {
            "Alarm" -> AlertMode.ALARM
            "Vibration" -> AlertMode.VIBRATION
            else -> AlertMode.BOTH
        }
        val label = when (option) {
            AlertMode.ALARM -> Res.string.mode_of_alert_option_1_label
            AlertMode.VIBRATION -> Res.string.mode_of_alert_option_2_label
            else -> Res.string.mode_of_alert_option_3_label
        }
        val selectionOption = SelectionOption(
            label = Res.string.mode_of_alert_option_1_label,
            option = AlertMode.VIBRATION
        )
        updateModeOfAlertOption(value = selectionOption)
    }
}