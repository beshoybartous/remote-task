package com.example.simpleremotetask.features.login.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginDto(
    var userName: String?,
    var token: String?,
)