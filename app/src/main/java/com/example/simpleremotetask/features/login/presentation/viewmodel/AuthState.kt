package com.example.simpleremotetask.features.login.presentation.viewmodel

data class AuthState(
    val isLoading: Boolean = false,
    val userName: String = "",
    val password: String = ""
)

sealed class AuthUiEvent {
    data class LoginUsernameChanged(val value: String) : AuthUiEvent()
    data class LoginPasswordChanged(val value: String) : AuthUiEvent()
    object Login : AuthUiEvent()
}