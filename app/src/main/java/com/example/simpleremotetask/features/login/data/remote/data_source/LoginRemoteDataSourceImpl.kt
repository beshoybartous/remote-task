package com.example.simpleremotetask.features.login.data.remote.data_source

import com.example.simpleremotetask.features.login.data.remote.api.LoginApi
import com.example.simpleremotetask.features.login.data.remote.dto.LoginDto

class LoginRemoteDataSourceImpl(
    private val loginApi: LoginApi
) : LoginRemoteDataSource {
    override suspend fun login(loginBody: HashMap<String, String>):
            LoginDto =
        loginApi.login(loginBody)

}