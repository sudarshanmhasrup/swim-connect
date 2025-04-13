package com.child.details.domain

import com.child.details.data.AlertTimeList
import com.child.details.model.SelectionOption

object AlertTimeUseCase {
    fun getAlertTimeOptions(): List<SelectionOption> {
        return AlertTimeList.getAlertTimeOptions()
    }
}