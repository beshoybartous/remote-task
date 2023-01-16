package com.example.simpleremotetask.features.login.data.mapper

import com.example.simpleremotetask.features.login.data.remote.dto.LoginDto
import com.example.simpleremotetask.features.login.domain.model.LoginModel

fun LoginDto.toLoginModel(): LoginModel {
    return LoginModel(
        userName = this.userName,
        token = this.token
    )
}