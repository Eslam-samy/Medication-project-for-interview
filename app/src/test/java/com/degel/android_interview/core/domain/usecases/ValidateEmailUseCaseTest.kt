package com.degel.android_interview.core.domain.usecases

import junit.framework.TestCase
import org.junit.Assert.*
import org.junit.Test
import com.degel.android_interview.core.domain.util.Result
import com.degel.android_interview.core.validators.EmailError

class ValidateEmailUseCaseTest{
    private val validateEmailUseCase = ValidateEmailUseCase()

    @Test
    fun `invoke should return Error when email is empty`() {
        // Arrange
        val email = ""

        // Act
        val result = validateEmailUseCase(email)

        TestCase.assertTrue(result is Result.Error)
        assertEquals(result, Result.Error(EmailError.IS_EMPTY))
    }


    @Test
    fun `invoke should return Error when email is invalid`() {
        // Arrange
        val email = "eewewe@wewe"

        val result = validateEmailUseCase(email)
        TestCase.assertTrue(result is Result.Error)
        assertEquals(result, Result.Error(EmailError.INVALID_EMAIL))

    }

    @Test
    fun `invoke should return Success when email is valid`() {
        val email = "esamy8088@gmail.com"

        val result = validateEmailUseCase(email)
        TestCase.assertTrue(result is Result.Success)
        assertEquals( Result.Success(Unit), result)

    }
}