package com.example.simpleremotetask.features.medicine.presentation.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleremotetask.features.medicine.domain.model.Medicine
import com.example.simpleremotetask.features.medicine.domain.use_case.GetGreetingMessageUseCase
import com.example.simpleremotetask.features.medicine.domain.use_case.GetMedicationsUseCase
import com.example.simpleremotetask.network.NetworkResult
import com.example.simpleremotetask.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MedicineHomeViewModel @Inject constructor(
    private val getMedicationsUseCase: GetMedicationsUseCase,
    private val getGreetingMessageUseCase: GetGreetingMessageUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<HomeUIState> = MutableStateFlow(
        HomeUIState.Loading
    )
    val stateFlow: StateFlow<HomeUIState>
        get() = _stateFlow

    var welcomeState by mutableStateOf("")

    init {
        viewModelScope.launch {
            val userName = savedStateHandle.get<String>(
                Constants.USER_NAME_ARGUMENT_KEY
            )


            welcomeState = "${
                getGreetingMessageUseCase(
                    Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                )
            } $userName"
            onEvent(HomeUIState.Loading)
        }
    }

    private suspend fun onEvent(event: HomeUIState) {
        when (event) {
            is HomeUIState.Loading -> {
                getMedicinesList()
            }
            is HomeUIState.Data -> {
                _stateFlow.emit(HomeUIState.Data(event.medicinesList))
            }
            is HomeUIState.Error -> {
                _stateFlow.emit(HomeUIState.Error(event.errorMessage))
            }

        }
    }

    private suspend fun getMedicinesList() {
        when (val medicines = getMedicationsUseCase()) {
            is NetworkResult.Success -> {
                onEvent(HomeUIState.Data(medicines.data))
            }
            is NetworkResult.Error -> {
                onEvent(HomeUIState.Error(errorMessage = medicines.message))
            }
        }
    }
}

sealed class HomeUIState {
    object Loading : HomeUIState()
    data class Data(val medicinesList: List<Medicine>?) : HomeUIState()
    data class Error(val errorMessage: String?) : HomeUIState()
}