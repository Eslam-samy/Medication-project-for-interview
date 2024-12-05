package com.degel.android_interview.features.medications.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DrugDto(
    val name: String? = null,
    val dose: String? = null,
    val strength: String? = null
)
