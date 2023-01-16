package com.example.simpleremotetask.features.login.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.simpleremotetask.features.login.domain.model.LoginModel
import com.example.simpleremotetask.features.login.domain.use_case.LoginUseCase
import com.example.simpleremotetask.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _loginFlow: MutableSharedFlow<NetworkResult<LoginModel>> =
        MutableSharedFlow()
    val loginFlow: SharedFlow<NetworkResult<LoginModel>>
        get() = _loginFlow

    var state by mutableStateOf(AuthState())

    fun onEvent(event: AuthUiEvent) {
        when (event) {
            is AuthUiEvent.LoginPasswordChanged -> {
                state = state.copy(password = event.value)
            }
            is AuthUiEvent.LoginUsernameChanged -> {
                state = state.copy(userName = event.value)
            }
            is AuthUiEvent.Login -> {
                login()
            }
        }
    }

    private fun login() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val loginResult =
                loginUseCase.invoke(
                    userName = state.userName,
                    password = state.password
                )
            _loginFlow.emit(loginResult)
            state = state.copy(isLoading = false)
        }
    }
}