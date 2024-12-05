package com.degel.android_interview.features.medications.di


import com.degel.android_interview.features.medications.data.local.MedicationDao
import com.degel.android_interview.features.medications.data.repository.MedicationsRepositoryImpl
import com.degel.android_interview.features.medications.domain.repository.MedicationsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.HttpClient


@Module
@InstallIn(ViewModelComponent::class)
object MedicationsModule {

    @Provides
    fun provideMedicationsRepository(
        httpClient: HttpClient,
        dao: MedicationDao,
    ): MedicationsRepository {
        return MedicationsRepositoryImpl(httpClient, dao)
    }
}