package com.example.simpleremotetask.features.login.domain.use_case

import com.example.simpleremotetask.features.login.domain.model.LoginModel
import com.example.simpleremotetask.features.login.domain.repository.LoginRepository
import com.example.simpleremotetask.network.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class LoginUseCaseTest {

    @Mock
    private lateinit var loginRepository: LoginRepository

    private lateinit var loginUseCase: LoginUseCase

    @Before
    fun setup() {
        loginUseCase = Mockito.spy(LoginUseCase(loginRepository))
    }

    @Test
    fun verifyLoginSuccess() = runTest {
        val userName = "dummyUser"
        val userPassword = "dummyPassword"

        Mockito.`when`(
            loginRepository.login(
                userName, userPassword
            )
        ).thenReturn(
            NetworkResult.Success(LoginModel("",userName))
        )

        val result = loginUseCase(userName, userPassword)

        Assert.assertEquals(result.data?.userName, userName)
        Assert.assertEquals(result.javaClass, NetworkResult.Success::class.java)
    }

    @Test
    fun verifyLoginError() = runTest {

        val errorMessage="something went wrong"
        Mockito.`when`(loginRepository.login("","")).thenReturn(
            NetworkResult.Error(errorMessage)
        )

        val result = loginUseCase("", "")

        Assert.assertEquals(result.message, errorMessage)

        Assert.assertEquals(result.javaClass, NetworkResult.Error::class.java)
    }

}