package com.degel.android_interview.features.medications.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.degel.android_interview.features.medications.domain.model.Drug

@Composable
fun DrugItemComponent(
    modifier: Modifier = Modifier,
    drug: Drug,
    onCLick: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // drug Name
            Text(
                text = drug.name ?: "",
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Strength
            Text(
                text = "Strength: ${drug.strength}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 4.dp)
            )

            // Dose
            val doseText = if (drug.dose.isNullOrEmpty()) "Not Specified" else drug.dose
            Text(
                text = "Dose: $doseText",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Action Button
            Button(
                onClick = onCLick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Details")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun drugCardPreview() {
    val drug = Drug(
        name = "Asprin",
        dose = "",
        strength = "500 mg"
    )
    DrugItemComponent(drug = drug)
}
