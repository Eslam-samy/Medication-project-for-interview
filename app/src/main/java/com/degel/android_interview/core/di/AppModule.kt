package com.degel.android_interview.core.di

import android.content.Context
import com.degel.android_interview.core.data.local.MedicineDataBase
import com.degel.android_interview.core.data.networking.HttpClientFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    // for data base singleton instance
    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext app: Context): MedicineDataBase {
        return MedicineDataBase.getInstance(app)

    }

    //for medication dao
    @Singleton
    @Provides
    fun provideMedicationDao(dataBase: MedicineDataBase) = dataBase.medicationDao()

    //for medication dao
    @Singleton
    @Provides
    fun provideUserDao(dataBase: MedicineDataBase) = dataBase.userDao()

    // for HttpClient singleton
    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory.create(CIO.create())  // Using CIO engine
    }

}