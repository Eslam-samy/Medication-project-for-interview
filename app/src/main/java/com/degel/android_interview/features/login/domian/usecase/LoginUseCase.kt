package com.degel.android_interview.features.login.domian.usecase

import com.degel.android_interview.core.domain.util.NetworkError
import com.degel.android_interview.core.domain.util.Result
import com.degel.android_interview.features.login.domian.model.User
import com.degel.android_interview.features.login.domian.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: LoginRepository,
) {
    suspend operator fun invoke(username: String, password: String): Result<User, NetworkError> {
        return repository.login(username, password)
    }
}