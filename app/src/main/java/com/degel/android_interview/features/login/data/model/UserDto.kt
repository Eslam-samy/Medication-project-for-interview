package com.degel.android_interview.features.login.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.degel.android_interview.features.login.domian.model.User
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "user")
data class UserDto(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
) {
    fun toUser(): User {
        return User(
            id = id,
            name = name
        )
    }
}

