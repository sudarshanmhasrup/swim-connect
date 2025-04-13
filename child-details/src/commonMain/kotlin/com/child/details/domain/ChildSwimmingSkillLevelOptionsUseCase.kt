package com.child.details.domain

import com.child.details.data.ChildSwimmingSkillLevelOptionsList
import com.child.details.model.SelectionOption

object ChildSwimmingSkillLevelOptionsUseCase {
    fun getChildSwimmingSkillLevelOptions(): List<SelectionOption> {
        return ChildSwimmingSkillLevelOptionsList.getChildSwimmingSkillLevelOptions()
    }
}