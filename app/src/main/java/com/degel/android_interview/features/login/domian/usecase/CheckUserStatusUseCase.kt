package com.degel.android_interview.features.login.domian.usecase

import com.degel.android_interview.features.login.domian.repository.LoginRepository
import javax.inject.Inject

class CheckUserStatusUseCase @Inject constructor(
    private val loginRepository: LoginRepository,

    ) {
    suspend operator fun invoke(): Boolean {
        return loginRepository.checkIfAlreadyLoggedIn()
    }
}