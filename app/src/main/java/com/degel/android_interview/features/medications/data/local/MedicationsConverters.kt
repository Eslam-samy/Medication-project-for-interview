package com.degel.android_interview.features.medications.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import com.degel.android_interview.features.medications.data.model.DrugDto

class MedicationConverters {

    // Convert List<Map<String, List<DrugDto>>> to a JSON string
    @TypeConverter
    fun fromMedicationsClasses(value: List<Map<String, List<Map<String, List<DrugDto>>>>>?): String {
        return value?.let { Json.encodeToString(it) }!!
    }

    // Convert JSON string back to List<Map<String, List<DrugDto>>>
    @TypeConverter
    fun toMedicationsClasses(value: String): List<Map<String, List<Map<String, List<DrugDto>>>>>? {
        return value.let { Json.decodeFromString(it) }
    }
}
