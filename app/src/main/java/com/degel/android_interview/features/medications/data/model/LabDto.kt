package com.degel.android_interview.features.medications.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LabDto(
    @SerialName("missing_field")
    val missingField: String? = null
)
