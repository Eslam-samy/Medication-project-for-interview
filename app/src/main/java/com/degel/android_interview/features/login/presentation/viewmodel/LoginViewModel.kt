package com.degel.android_interview.features.login.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.degel.android_interview.core.domain.util.onError
import com.degel.android_interview.core.domain.util.onSuccess
import com.degel.android_interview.features.login.domian.usecase.CheckUserStatusUseCase
import com.degel.android_interview.features.login.domian.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


data class LoginUiState(
    val isLoading: Boolean = false,
    val username: String = "",
    val password: String = "",
    val error: String? = null,
    val isLoggedIn: Boolean = false,
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val checkIfAlreadyLoggedInUseCase: CheckUserStatusUseCase,
) : ViewModel() {
    private val _state = MutableStateFlow(LoginUiState())
    val state = _state.onStart {
        checkLogonState()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        LoginUiState()
    )
    private val _event = Channel<String>()
    val event = _event.receiveAsFlow()

    private fun checkLogonState() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoggedIn = checkIfAlreadyLoggedInUseCase())
        }
    }

    fun setUsername(it: String) {
        _state.value = _state.value.copy(username = it)
    }

    fun setPassword(it: String) {
        _state.value = _state.value.copy(password = it)
    }

    fun performLogin() {
        _state.value = _state.value.copy(isLoading = true)
        if (_state.value.username.isEmpty() || _state.value.password.isEmpty()) {
            _event.trySend("Username or password cannot be empty")
            _state.value = _state.value.copy(isLoading = false)
            return
        }
        viewModelScope.launch {
            val loginResult = loginUseCase(_state.value.username, _state.value.password)
            loginResult.onSuccess {
                _state.value = _state.value.copy(isLoading = false)
                _state.value = _state.value.copy(isLoggedIn = true)
            }.onError { error ->
                _state.value = _state.value.copy(isLoading = false)
                _event.trySend(error.toString())
            }


        }
    }
}