package com.degel.android_interview.core.validators

import com.degel.android_interview.core.domain.util.DomainError


enum class EmailError : DomainError {
    IS_EMPTY,
    INVALID_EMAIL
}

enum class PasswordError : DomainError {
    IS_EMPTY,
    WEAK_PASSWORD,
    DOES_NOT_CONTAINS_UPPERCASE,
    DOES_NOT_CONTAINS_SPECIAL_CHAR
}
