package com.example.simpleremotetask.features.medicine.presentation.details.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.simpleremotetask.R
import com.example.simpleremotetask.common.ui.components.EmptyScreen
import com.example.simpleremotetask.common.ui.components.LoadingScreen
import com.example.simpleremotetask.features.medicine.domain.model.Medicine
import com.example.simpleremotetask.features.medicine.presentation.details.viewmodel.DetailUIState
import com.example.simpleremotetask.features.medicine.presentation.details.viewmodel.MedicineDetailViewModel

@Composable
fun DetailsScreen(
    medicineDetailViewModel: MedicineDetailViewModel = hiltViewModel()
) {
    val state by medicineDetailViewModel.medicineFlow.collectAsStateWithLifecycle()

    when (state) {
        is DetailUIState.Loading -> {
            (state as DetailUIState.Loading).medicineId?.let {
                LoadingScreen(show = true)
            }
        }
        is DetailUIState.Data -> {
            val medicine = (state as DetailUIState.Data).medicine
            DetailsCard(medicine)
        }
        is DetailUIState.Error -> {
            EmptyScreen((state as DetailUIState.Error).errorMessage)
        }
    }
}

@Composable
fun DetailsCard(medicine: Medicine) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(4.dp))
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(128.dp),
                    painter = painterResource(R.drawable.image_medicine),
                    contentDescription = "Medicine Image",
                    contentScale = ContentScale.Crop
                )
            }
            Text(
                text = medicine.medicineName,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
            )
            Text(
                text = "Does: ${medicine.medicineDose} Times",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = medicine.medicineStrength,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}
