package com.degel.android_interview.features.medications.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.degel.android_interview.features.medications.domain.model.Drug

@Composable
fun DrugDetailContent(
    drug: Drug,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Name
        Text(
            text = drug.name,
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Dose and Strength
        DetailItem(label = "Dose", value = drug.dose.ifEmpty { "Not specified" })
        DetailItem(label = "Strength", value = drug.strength)

        // Divider
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))

        // Additional Information (if needed)
        Text(
            text = "This drug is commonly used for medical purposes. Always follow your doctor’s prescription.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}
