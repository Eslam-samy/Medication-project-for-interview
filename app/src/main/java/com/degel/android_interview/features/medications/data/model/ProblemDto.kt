package com.degel.android_interview.features.medications.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ProblemDto(
    val Diabetes: List<ConditionDto>? = null,
    val Asthma: List<ConditionDto>? = null
)
