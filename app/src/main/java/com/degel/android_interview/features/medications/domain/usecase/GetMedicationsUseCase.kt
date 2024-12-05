package com.degel.android_interview.features.medications.domain.usecase

import com.degel.android_interview.core.domain.util.NetworkError
import com.degel.android_interview.core.domain.util.Result
import com.degel.android_interview.features.login.domian.model.User
import com.degel.android_interview.features.medications.domain.model.Medication
import com.degel.android_interview.features.medications.domain.repository.MedicationsRepository
import javax.inject.Inject

class GetMedicationsUseCase @Inject constructor(
    private val medicationsRepository: MedicationsRepository,
) {
    suspend operator fun invoke(): Result<List<Medication>, NetworkError> {
        return medicationsRepository.getMedications()
    }
}