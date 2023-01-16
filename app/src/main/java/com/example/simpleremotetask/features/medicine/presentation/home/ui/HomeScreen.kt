package com.example.simpleremotetask.features.medicine.presentation.home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.simpleremotetask.R
import com.example.simpleremotetask.common.ui.components.EmptyScreen
import com.example.simpleremotetask.common.ui.components.LoadingScreen
import com.example.simpleremotetask.features.medicine.domain.model.Medicine
import com.example.simpleremotetask.features.medicine.presentation.home.viewmodel.HomeUIState
import com.example.simpleremotetask.features.medicine.presentation.home.viewmodel.MedicineHomeViewModel
import com.example.simpleremotetask.navigation.Screen

@Composable
fun HomeScreen(
    navController: NavHostController,
    medicineHomeViewModel: MedicineHomeViewModel = hiltViewModel()
) {
    val state by medicineHomeViewModel.stateFlow.collectAsStateWithLifecycle()
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 8.dp,
                    end = 16.dp
                ),
                text = medicineHomeViewModel.welcomeState,
                style = TextStyle(
                    fontSize = 28.sp,
                    fontFamily = FontFamily.Serif
                )
            )
            when (state) {
                is HomeUIState.Loading -> {
                    LoadingScreen(show = true)
                }
                is HomeUIState.Data -> {
                    val medicines = (state as HomeUIState.Data).medicinesList
                    if (!medicines.isNullOrEmpty())
                        MedicineList(
                            navController = navController, medicines =
                            medicines
                        )
                    else
                        EmptyScreen("No data found")
                }
                is HomeUIState.Error -> {
                    EmptyScreen((state as HomeUIState.Error).errorMessage)
                }
            }

        }
    }

}

@Composable
fun MedicineList(
    navController: NavHostController,
    medicines: List<Medicine>
) {
    LazyColumn(contentPadding = PaddingValues(8.dp)) {
        items(medicines) {
            Box(
                modifier = Modifier
                    .padding(8.dp),
            ) {
                MedicineCard(navController, it)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MedicineCard(
    navController: NavHostController,
    medicine: Medicine
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 128.dp),
        elevation = 10.dp,
        onClick = {
            navController.navigate(Screen.Details.passMedicineId(medicineId = medicine.id))
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .padding(4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
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