package com.child.details.domain

import com.child.details.data.AlertModeList
import com.child.details.model.SelectionOption

object AlertModeUseCase {
    fun getAlertModeOptionList(): List<SelectionOption> {
        return AlertModeList.getAlertModeOptionList()
    }
}