package com.degel.android_interview.core.domain.usecases

import com.degel.android_interview.core.validators.PasswordError
import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Test
import com.degel.android_interview.core.domain.util.Result

class ValidatePasswordUseCaseTest {

    private val validatePasswordUseCase = ValidatePasswordUseCase()

    @Test
    fun `invoke empty password should return error`() {
        val password = ""

        val result = validatePasswordUseCase(password)

        TestCase.assertTrue(result is Result.Error)
        assertEquals(Result.Error(PasswordError.IS_EMPTY), result)
    }

    @Test
    fun `invoke password with length less than 8 should return weak password error`() {
        val password = "pas123"

        val result = validatePasswordUseCase(password)

        TestCase.assertTrue(result is Result.Error)
        assertEquals(Result.Error(PasswordError.WEAK_PASSWORD), result)
    }

    @Test
    fun `invoke password with length less than 8 and without uppercase should return weak password error`() {
        val password = "pasd123"

        val result = validatePasswordUseCase(password)

        TestCase.assertTrue(result is Result.Error)
        assertEquals(Result.Error(PasswordError.WEAK_PASSWORD), result)
    }

    @Test
    fun `invoke password with length less than 8 and without special char should return weak password error`() {
        val password = "Pasd123"

        val result = validatePasswordUseCase(password)

        TestCase.assertTrue(result is Result.Error)
        assertEquals(Result.Error(PasswordError.WEAK_PASSWORD), result)
    }

    @Test
    fun `invoke password without uppercase should return error`() {
        val password = "password123!"

        val result = validatePasswordUseCase(password)

        TestCase.assertTrue(result is Result.Error)
        assertEquals(Result.Error(PasswordError.DOES_NOT_CONTAINS_UPPERCASE), result)
    }

    @Test
    fun `invoke password without special char should return error`() {
        val password = "Password123"

        val result = validatePasswordUseCase(password)

        TestCase.assertTrue(result is Result.Error)
        assertEquals(Result.Error(PasswordError.DOES_NOT_CONTAINS_SPECIAL_CHAR), result)
    }

    @Test
    fun `invoke valid password should return success`() {
        val password = "Password123!"

        val result = validatePasswordUseCase(password)

        TestCase.assertTrue(result is Result.Success)
        assertEquals(Result.Success(Unit), result)
    }

    @Test
    fun `invoke password with all conditions met should return success`() {
        val password = "ValidPassword123!"

        val result = validatePasswordUseCase(password)

        TestCase.assertTrue(result is Result.Success)
        assertEquals(Result.Success(Unit), result)
    }
}