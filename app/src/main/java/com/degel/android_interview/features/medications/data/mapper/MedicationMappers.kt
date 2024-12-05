package com.degel.android_interview.features.medications.data.mapper

import com.degel.android_interview.features.medications.data.model.DrugDto
import com.degel.android_interview.features.medications.data.model.MedicationDto
import com.degel.android_interview.features.medications.domain.model.Drug
import com.degel.android_interview.features.medications.domain.model.Medication


fun DrugDto.toDrug(): Drug {
    return Drug(
        name = name ?: "",
        dose = dose ?: "",
        strength = strength ?: ""
    )
}

fun MedicationDto.toMedication(): Medication {
    return Medication(
        id = id,
        medicationsClasses = medicationsClasses.map {
            it.mapValues { (_, value) ->
                value.map { drugDto ->
                    drugDto.mapValues { (_, drugList) ->
                        drugList.map { it.toDrug() }
                    }
                }
            }
        }
    )
}

