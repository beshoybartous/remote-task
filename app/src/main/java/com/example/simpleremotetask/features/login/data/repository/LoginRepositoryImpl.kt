package com.example.simpleremotetask.features.login.data.repository

import com.example.simpleremotetask.features.login.data.mapper.toLoginModel
import com.example.simpleremotetask.features.login.data.remote.data_source.LoginRemoteDataSource
import com.example.simpleremotetask.features.login.domain.model.LoginModel
import com.example.simpleremotetask.features.login.domain.repository.LoginRepository
import com.example.simpleremotetask.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource
) : LoginRepository {
    override suspend fun login(userName: String, password: String):
            NetworkResult<LoginModel> =
        withContext(Dispatchers.IO) {
            try {
                val response =
                    loginRemoteDataSource.login(
                        loginBody = hashMapOf
                            ("userName" to userName, "password" to password)
                    )

                val loginModel = response.toLoginModel()
                loginModel.userName = userName
                NetworkResult.Success(loginModel)
            } catch (e: Exception) {
                NetworkResult.Error(e.message)
            }
        }
}