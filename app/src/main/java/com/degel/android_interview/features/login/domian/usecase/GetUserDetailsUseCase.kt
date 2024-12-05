package com.degel.android_interview.features.login.domian.usecase

import com.degel.android_interview.core.domain.util.NetworkError
import com.degel.android_interview.core.domain.util.Result
import com.degel.android_interview.features.login.domian.model.User
import com.degel.android_interview.features.login.domian.repository.LoginRepository
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {

    suspend operator fun invoke(): Result<User, NetworkError> {
        return loginRepository.getUseDetails()
    }


}