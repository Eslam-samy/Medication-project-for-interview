package com.degel.android_interview.features.medications.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HealthResponse(
    val problems: List<ProblemDto>? = null
)
