package com.degel.android_interview.core.domain.usecases

import com.degel.android_interview.core.domain.util.Result
import com.degel.android_interview.core.validators.PasswordError


class ValidatePasswordUseCase {
    operator fun invoke(password: String): Result<Unit, PasswordError> {
        if (password.isEmpty()) {
            return Result.Error(PasswordError.IS_EMPTY)
        }
        if (password.length < 8) {
            return Result.Error(PasswordError.WEAK_PASSWORD)
        }
        if (!password.contains(Regex("[A-Z]"))) {
            return Result.Error(PasswordError.DOES_NOT_CONTAINS_UPPERCASE)
        }
        if (!password.contains(Regex("[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]"))) {
            return Result.Error(PasswordError.DOES_NOT_CONTAINS_SPECIAL_CHAR)
        }
        return Result.Success(Unit)
    }
}
