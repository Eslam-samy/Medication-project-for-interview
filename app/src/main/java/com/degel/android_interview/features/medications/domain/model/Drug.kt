package com.degel.android_interview.features.medications.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class Drug(
    val name: String = "",
    val dose: String = "",
    val strength: String = "",
)