package com.degel.android_interview.features.medications.data.repository

import android.util.Log
import com.degel.android_interview.core.data.networking.SafeCall
import com.degel.android_interview.core.data.networking.constructUrl
import com.degel.android_interview.core.domain.util.NetworkError
import com.degel.android_interview.core.domain.util.Result
import com.degel.android_interview.core.domain.util.map
import com.degel.android_interview.features.medications.data.local.MedicationDao
import com.degel.android_interview.features.medications.data.mapper.toMedication
import com.degel.android_interview.features.medications.data.model.HealthResponse
import com.degel.android_interview.features.medications.domain.model.Medication
import com.degel.android_interview.features.medications.domain.repository.MedicationsRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.flow.map
import java.time.ZoneId
import javax.inject.Inject

class MedicationsRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val medicationDao: MedicationDao,
) : MedicationsRepository {

    override suspend fun getMedications(): Result<List<Medication>, NetworkError> {
        // Try to fetch from the network
        val networkResult = SafeCall<HealthResponse> {
            httpClient.get(
                urlString = constructUrl(
                    url = "4a2cdddf-9b7f-42b1-93d5-9557010c181a"
                )
            )
        }
        return when (networkResult) {
            is Result.Success -> {
                val medications = networkResult.data.problems
                    ?.firstOrNull()
                    ?.Diabetes
                    ?.firstOrNull()
                    ?.medications
                    ?: listOf()

                Log.d("ESLAMDEGEL", "getMedications:  " + medications)
                // Save fetched data to database
                medicationDao.clearDataBase()
                medicationDao.insertMedications(medications)

                Result.Success(medications.map { it.toMedication() })
            }

            is Result.Error -> {
                // If network fails, fetch data from the database
                val cachedMedications = medicationDao.getMedications().map { it.toMedication() }
                if (cachedMedications.isNotEmpty()) {
                    Result.Success(cachedMedications)
                } else {
                    Result.Error(networkResult.error) // Replace with your specific error
                }
            }
        }
    }
}
