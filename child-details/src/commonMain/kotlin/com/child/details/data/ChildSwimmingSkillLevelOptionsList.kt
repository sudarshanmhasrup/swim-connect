package com.child.details.data

import com.child.details.child_details.generated.resources.Res
import com.child.details.child_details.generated.resources.child_swimming_skill_level_option_1_label
import com.child.details.child_details.generated.resources.child_swimming_skill_level_option_2_label
import com.child.details.child_details.generated.resources.child_swimming_skill_level_option_3_label
import com.child.details.model.ChildSwimmingSkillLevel
import com.child.details.model.SelectionOption

object ChildSwimmingSkillLevelOptionsList {
    private val childSwimmingSkillLevelOptionList = listOf(
        SelectionOption(
            label = Res.string.child_swimming_skill_level_option_1_label,
            option = ChildSwimmingSkillLevel.UNSKILLED,
        ),
        SelectionOption(
            label = Res.string.child_swimming_skill_level_option_2_label,
            option = ChildSwimmingSkillLevel.SEMI_SKILLED,
        ),
        SelectionOption(
            label = Res.string.child_swimming_skill_level_option_3_label,
            option = ChildSwimmingSkillLevel.SKILLED,
        )
    )

    fun getChildSwimmingSkillLevelOptions(): List<SelectionOption> {
        return childSwimmingSkillLevelOptionList
    }
}