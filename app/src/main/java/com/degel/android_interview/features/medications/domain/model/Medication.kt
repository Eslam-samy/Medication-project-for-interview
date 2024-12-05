package com.degel.android_interview.features.medications.domain.model

import com.degel.android_interview.features.medications.data.model.DrugDto


data class Medication(
    val id: Int = 0,
    val medicationsClasses: List<Map<String, List<Map<String, List<Drug>>>>> = listOf(),
)