package com.degel.android_interview.features.login.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.degel.android_interview.features.login.data.model.UserDto

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserDto)

    @Query("SELECT COUNT(*) FROM user")
    suspend fun getUserListCount(): Int

    @Query("SELECT * FROM user")
    suspend fun getUserList(): List<UserDto>

    @Query("DELETE FROM user")
    suspend fun removeUser()

}