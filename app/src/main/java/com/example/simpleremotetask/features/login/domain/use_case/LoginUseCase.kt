package com.example.simpleremotetask.features.login.domain.use_case

import com.example.simpleremotetask.features.login.domain.model.LoginModel
import com.example.simpleremotetask.features.login.domain.repository.LoginRepository
import com.example.simpleremotetask.network.NetworkResult
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(
        userName: String,
        password: String
    ): NetworkResult<LoginModel> =
        loginRepository.login(userName, password)

}