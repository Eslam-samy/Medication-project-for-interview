package com.degel.android_interview.features.medications.data.model

import kotlinx.serialization.Serializable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Serializable
@Entity(tableName = "medications")
data class MedicationDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val medicationsClasses: List<Map<String, List<Map<String, List<DrugDto>>>>> = listOf(),
)
