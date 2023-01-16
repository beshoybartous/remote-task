package com.example.simpleremotetask.features.login.data.repository

import com.example.simpleremotetask.features.login.data.remote.data_source.LoginRemoteDataSource
import com.example.simpleremotetask.features.login.data.remote.dto.LoginDto
import com.example.simpleremotetask.features.login.domain.repository.LoginRepository
import com.example.simpleremotetask.network.NetworkResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.net.SocketException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class LoginRepositoryImplTest {

    @Mock
    private lateinit var loginRemoteDataSource: LoginRemoteDataSource

    private lateinit var loginRepository: LoginRepository

    @Before
    fun setup() {
        loginRepository =
            Mockito.spy(
                LoginRepositoryImpl(
                    loginRemoteDataSource
                )
            )
    }

    @Test
    fun verifyLoginSuccess() = runTest {
        val userName = "dummyUser"
        val userPassword = "dummyPassword"

        Mockito.`when`(
            loginRemoteDataSource.login(
                hashMapOf(
                    "userName" to
                            userName,
                    "password" to userPassword
                )
            )
        ).thenReturn(
            LoginDto(userName = userName, token = "dummyToken")
        )

        val result = loginRepository.login(userName, userPassword)

        Assert.assertEquals(result.javaClass, NetworkResult.Success::class.java)
        Assert.assertEquals(result.data?.userName, userName)
    }

    @Test
    fun verifyLoginError() = runTest {

        Mockito.`when`(loginRemoteDataSource.login(hashMapOf("" to ""))).thenAnswer{
            throw SocketException()
        }


        val result = loginRepository.login("", "")

        Assert.assertEquals(result.javaClass, NetworkResult.Error::class.java)
    }

    @After
    fun tearDown(){
        Mockito.reset(loginRemoteDataSource)

    }
}