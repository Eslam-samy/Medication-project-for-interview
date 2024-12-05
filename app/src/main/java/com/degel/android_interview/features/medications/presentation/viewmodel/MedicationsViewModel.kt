package com.degel.android_interview.features.medications.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.degel.android_interview.core.domain.util.NetworkError
import com.degel.android_interview.core.domain.util.onError
import com.degel.android_interview.core.domain.util.onSuccess
import com.degel.android_interview.features.login.domian.usecase.GetUserDetailsUseCase
import com.degel.android_interview.features.login.domian.usecase.LogoutUseCase
import com.degel.android_interview.features.login.presentation.viewmodel.LoginUiState
import com.degel.android_interview.features.medications.domain.model.Drug
import com.degel.android_interview.features.medications.domain.model.Medication
import com.degel.android_interview.features.medications.domain.usecase.GetMedicationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

data class MedicationsUiState(
    val drugs: List<Drug> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val userName: String? = null,
)


@HiltViewModel
class MedicationsViewModel @Inject constructor(
    private val getMedicationsUseCase: GetMedicationsUseCase,
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    private val logoutUseCase: LogoutUseCase,
) : ViewModel(

) {

    private val _state = MutableStateFlow(MedicationsUiState())
    val state = _state.onStart {
        getUserDetails()
        getMedications()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        MedicationsUiState()

    )

    private fun getUserDetails() {
        viewModelScope.launch {
            getUserDetailsUseCase()
                .onSuccess {
                    _state.value = _state.value.copy(
                        userName = it.name
                    )
                }.onError { error ->
                    _event.send(error)
                }
        }
    }

    private val _event = Channel<NetworkError>()
    val event = _event.receiveAsFlow()
    private val _logoutEvent = Channel<Boolean>()
    val logoutEvent = _logoutEvent.receiveAsFlow()

    private fun getMedications() {
        viewModelScope.launch {
            getMedicationsUseCase()
                .onSuccess {
                    _state.value = _state.value.copy(
                        drugs = extractDrugs(it),
                        isLoading = false
                    )

                }.onError { error ->
                    _state.value = _state.value.copy(isLoading = false)
                    _event.send(error)
                }
        }
    }


    // there is some problems in the given api and need to be handeled in better way
    private fun extractDrugs(medications: List<Medication>?): List<Drug> {
        return medications.orEmpty().flatMap { medication ->
            medication.medicationsClasses.flatMap { classMap ->
                classMap.values.flatMap { drugListMap ->
                    drugListMap.flatMap { drugMap ->
                        drugMap.values.flatten()
                    }
                }
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            logoutUseCase().onSuccess {
                _logoutEvent.send(true)
            }
        }
    }
}