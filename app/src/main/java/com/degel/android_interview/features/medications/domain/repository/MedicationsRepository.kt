package com.degel.android_interview.features.medications.domain.repository

import com.degel.android_interview.core.domain.util.NetworkError
import com.degel.android_interview.core.domain.util.Result
import com.degel.android_interview.features.medications.domain.model.Medication

interface MedicationsRepository {

    suspend fun getMedications(): Result<List<Medication>, NetworkError>
}