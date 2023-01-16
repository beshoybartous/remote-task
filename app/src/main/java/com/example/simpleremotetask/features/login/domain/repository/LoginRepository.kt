package com.example.simpleremotetask.features.login.domain.repository

import com.example.simpleremotetask.features.login.domain.model.LoginModel
import com.example.simpleremotetask.network.NetworkResult

interface LoginRepository {
    suspend fun login(userName: String, password: String):
            NetworkResult<LoginModel>
}