package com.example.simpleremotetask.features.login.data.remote.data_source

import com.example.simpleremotetask.features.login.data.remote.dto.LoginDto

interface LoginRemoteDataSource {
    suspend fun login(loginBody: HashMap<String, String>): LoginDto
}