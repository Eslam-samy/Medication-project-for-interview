package com.degel.android_interview.features.medications.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.degel.android_interview.features.medications.domain.model.Drug
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedDrugViewModel : ViewModel() {
    private val _selectedDrug = MutableStateFlow<Drug?>(null)
    val selectedDrug: StateFlow<Drug?> = _selectedDrug.asStateFlow()


    fun onSelectedDrug(drug: Drug?) {
        _selectedDrug.value = drug
    }
}