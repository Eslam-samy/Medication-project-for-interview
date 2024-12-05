package com.degel.android_interview.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.degel.android_interview.features.medications.data.local.MedicationConverters
import com.degel.android_interview.features.medications.data.local.MedicationDao
import com.degel.android_interview.features.medications.data.model.MedicationDto
import com.degel.android_interview.features.login.data.local.UserDao
import com.degel.android_interview.features.login.data.model.UserDto

@Database(
    version = 1,
    entities = [MedicationDto::class, UserDto::class]
)
@TypeConverters(MedicationConverters::class)
abstract class MedicineDataBase : RoomDatabase() {

    companion object {
        fun getInstance(context: Context): MedicineDataBase =
            Room.databaseBuilder(context, MedicineDataBase::class.java, "reminder_db").build()
    }

    abstract fun medicationDao(): MedicationDao
    abstract fun userDao(): UserDao
}