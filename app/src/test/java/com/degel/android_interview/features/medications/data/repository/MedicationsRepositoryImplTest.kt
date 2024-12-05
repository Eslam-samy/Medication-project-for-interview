package com.degel.android_interview.features.medications.data.repository

import org.junit.Assert.*

import com.degel.android_interview.features.login.data.local.UserDao

import com.degel.android_interview.features.login.data.repository.LoginRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import com.degel.android_interview.core.domain.util.Result

class LoginRepositoryImplTest {

    private val mockDao = mockk<UserDao>(relaxed = true)
    private val repository = LoginRepositoryImpl(httpClient = mockk(), dao = mockDao)

    @Test
    fun `login inserts user into database and returns success`() = runTest {
        // Arrange
        val username = "test_user"
        val password = "password123"

        // Act
        val result = repository.login(username, password)

        // Assert
        assertTrue(result is Result.Success)
        assertEquals(username, (result as Result.Success).data.name)

        // Verify database interaction
        coVerify { mockDao.insertUser(any()) }
    }
}
