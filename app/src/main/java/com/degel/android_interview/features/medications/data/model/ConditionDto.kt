package com.degel.android_interview.features.medications.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ConditionDto(
    val medications: List<MedicationDto>? = null,
    val labs: List<LabDto>? = null
)
