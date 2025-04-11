package com.child.details.domain

import com.child.details.model.AlertMode
import com.child.details.model.AlertTime
import com.child.details.model.ChildSwimmingSkillLevel

data class ChildDetails(
    val name: String,
    val age: String,
    val childSwimmingSkillLevel: ChildSwimmingSkillLevel,
    val alertTime: AlertTime,
    val customTime: String?,
    val modeOfAlert: AlertMode
)