package com.degel.android_interview.core.domain.usecases

import com.degel.android_interview.core.validators.EmailError
import com.degel.android_interview.core.domain.util.Result

class ValidateEmailUseCase {
    private val emailRegex = (
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
            ).toRegex()

    operator fun invoke(email: String): Result<Unit, EmailError> {
        if (email.isEmpty()) {
            return Result.Error(EmailError.IS_EMPTY)
        }
        if (!emailRegex.matches(email)) {
            return Result.Error(EmailError.INVALID_EMAIL)
        }
        return Result.Success(Unit)
    }
}