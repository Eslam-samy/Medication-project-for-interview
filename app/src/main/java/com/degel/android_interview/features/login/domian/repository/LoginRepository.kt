package com.degel.android_interview.features.login.domian.repository

import com.degel.android_interview.core.domain.util.NetworkError
import com.degel.android_interview.core.domain.util.Result
import com.degel.android_interview.features.login.domian.model.User

interface LoginRepository {

    // to create login request
    suspend fun login(
        username: String,
        password: String,
    ): Result<User, NetworkError>

    //to check if the user is already logged in or not
    suspend fun checkIfAlreadyLoggedIn(): Boolean

    // to create login request
    suspend fun logout(
    ): Result<Boolean, NetworkError>

    suspend fun getUseDetails(): Result<User, NetworkError>

}