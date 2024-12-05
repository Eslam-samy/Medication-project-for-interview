package com.degel.android_interview.features.login.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.degel.android_interview.features.login.presentation.viewmodel.LoginViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.degel.android_interview.core.presentation.components.CommonTextField
import com.degel.android_interview.core.presentation.components.CustomButton
import com.degel.android_interview.core.presentation.util.ObserveAsEvents

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel<LoginViewModel>(), // Get the ViewModel using Hilt
    // Callback to navigate to the next screen
    onLoginSuccess: () -> Unit,
) {

    // Observing the UI state from the ViewModel
    val uiState by viewModel.state.collectAsState()
    // Navigate to the next screen if logged in
    if (uiState.isLoggedIn) {
        onLoginSuccess() // Navigate to the next screen
    }
    val context = LocalContext.current

    ObserveAsEvents(events = viewModel.event) { event ->

        Toast.makeText(
            context,
            event,
            Toast.LENGTH_SHORT
        ).show()

    }

// Render the LoginScreen UI
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            verticalArrangement = Arrangement.Center
        ) {
            CommonTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.username,
                label = "Username or Email"
            ) {
                viewModel.setUsername(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            CommonTextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.password,
                label = "Password",
                isPassword = true
            ) {
                viewModel.setPassword(it)
            }
            Spacer(modifier = Modifier.height(16.dp))
            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = "Login",
                isLoading = uiState.isLoading
            ) {
                viewModel.performLogin()
            }
        }
    }


}