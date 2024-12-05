package com.degel.android_interview.features.login.data.repository

import android.util.Log
import com.degel.android_interview.core.data.local.MedicineDataBase
import com.degel.android_interview.core.domain.util.NetworkError
import com.degel.android_interview.core.domain.util.Result
import com.degel.android_interview.features.login.data.local.UserDao
import com.degel.android_interview.features.login.data.model.UserDto
import com.degel.android_interview.features.login.domian.model.User
import com.degel.android_interview.features.login.domian.repository.LoginRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val dao: UserDao,
) : LoginRepository {
    override suspend fun login(username: String, password: String): Result<User, NetworkError> =
        withContext(Dispatchers.IO) {
            dao.insertUser(
                UserDto(
                    name = username
                )
            )
            Result.Success(
                User(
                    id = 1,
                    name = username
                )
            )
        }

    override suspend fun checkIfAlreadyLoggedIn(): Boolean = withContext(Dispatchers.IO) {
        dao.getUserListCount() > 0
    }

    override suspend fun logout(): Result<Boolean, NetworkError> =
        withContext(Dispatchers.IO) {
            dao.removeUser()
            Result.Success(
               true
            )
        }


    override suspend fun getUseDetails(): Result<User, NetworkError> = withContext(Dispatchers.IO) {
        return@withContext try {
            val user = dao.getUserList().first().toUser()
            Result.Success(user)

        } catch (
            e: Exception,
        ) {
            Result.Error(NetworkError.UNKNOWN)
        }
    }


}