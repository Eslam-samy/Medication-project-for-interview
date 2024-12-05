package com.degel.android_interview.features.medications.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.degel.android_interview.core.presentation.util.ObserveAsEvents
import com.degel.android_interview.core.presentation.util.toString
import com.degel.android_interview.features.medications.domain.model.Drug
import com.degel.android_interview.features.medications.presentation.components.DrugItemComponent
import com.degel.android_interview.features.medications.presentation.components.GreetingColumn
import com.degel.android_interview.features.medications.presentation.components.MainAppLoader
import com.degel.android_interview.features.medications.presentation.viewmodel.MedicationsViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MedicationsViewModel = hiltViewModel<MedicationsViewModel>(), // Get the ViewModel using Hilt
    onDrugClick: (Drug) -> Unit = {},
    onLogout: () -> Unit = {},
) {
    val uiState by viewModel.state.collectAsState()
    val context = LocalContext.current
    ObserveAsEvents(events = viewModel.event) { event ->
        Toast.makeText(
            context,
            event.toString(context),
            Toast.LENGTH_SHORT
        ).show()
    }
    ObserveAsEvents(events = viewModel.logoutEvent) { event ->
        if (event) onLogout()
    }


    Scaffold(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (uiState.isLoading) {
            MainAppLoader(
                modifier = Modifier.padding(it)
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .padding(it)
            ) {
                item {
                    GreetingColumn(
                        userName = uiState.userName.orEmpty(),
                        onLogoutClick = {
                            viewModel.logout()
                        }
                    )
                }
                items(
                    uiState.drugs,
                ) { drug ->
                    DrugItemComponent(
                        drug = drug,
                        onCLick = {
                            onDrugClick(drug)
                        }
                    )
                }
            }
        }
    }

}
