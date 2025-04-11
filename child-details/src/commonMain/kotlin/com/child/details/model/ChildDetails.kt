package com.child.details.model

data class ChildDetails(
    val name: String,
    val age: String,
    val childSwimmingSkillLevel: ChildSwimmingSkillLevel,
    val alertTime: AlertTime,
    val customTime: String?,
    val modeOfAlert: AlertMode
)