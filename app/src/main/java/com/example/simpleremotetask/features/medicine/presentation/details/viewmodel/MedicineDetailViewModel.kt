package com.example.simpleremotetask.features.medicine.presentation.details.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleremotetask.features.medicine.domain.model.Medicine
import com.example.simpleremotetask.features.medicine.domain.use_case.GetMedicationByIDUseCase
import com.example.simpleremotetask.utils.Constants.DETAILS_ARGUMENT_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineDetailViewModel @Inject constructor(
    private val getMedicationByIDUseCase: GetMedicationByIDUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _medicine: MutableStateFlow<DetailUIState> =
        MutableStateFlow(DetailUIState.Loading(null))
    val medicineFlow: StateFlow<DetailUIState> = _medicine

    init {
        viewModelScope.launch {
            val medicineId = savedStateHandle.get<Int>(DETAILS_ARGUMENT_KEY)
            onEvent(DetailUIState.Loading(medicineId))
        }
    }

    private suspend fun getMedicineById(medicineId: Int?) {
        val medicine = medicineId?.let {
            getMedicationByIDUseCase(id = medicineId)
        }
        medicine?.let {
            onEvent(DetailUIState.Data(medicine = medicine))
        } ?: run {
            onEvent(DetailUIState.Error("Something went wrong!"))
        }
    }

    private suspend fun onEvent(event: DetailUIState) {
        when (event) {
            is DetailUIState.Loading -> {
                getMedicineById(event.medicineId)
            }
            is DetailUIState.Data -> {
                _medicine.emit(DetailUIState.Data(event.medicine))
            }
            is DetailUIState.Error -> {
                _medicine.emit(DetailUIState.Error(event.errorMessage))
            }

        }
    }
}

sealed class DetailUIState {
    data class Loading(val medicineId: Int?) : DetailUIState()
    data class Data(val medicine: Medicine) : DetailUIState()
    data class Error(val errorMessage: String) : DetailUIState()
}
