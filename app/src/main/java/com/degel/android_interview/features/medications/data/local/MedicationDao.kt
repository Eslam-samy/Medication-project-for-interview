package com.degel.android_interview.features.medications.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.degel.android_interview.features.medications.data.model.MedicationDto
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedications(medications: List<MedicationDto>)

    @Query("SELECT * FROM medications")
     suspend fun getMedications(): List<MedicationDto>

     @Query("DELETE FROM medications")
     suspend fun clearDataBase()
}