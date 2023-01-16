package com.example.simpleremotetask.features.login.data.remote.data_source

import com.example.simpleremotetask.features.login.data.remote.api.LoginApi
import com.example.simpleremotetask.features.login.data.remote.dto.LoginDto
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class LoginRemoteDataSourceImplTest {
    @Mock
    private lateinit var loginApi: LoginApi

    private lateinit var loginRemoteDataSource: LoginRemoteDataSource

    @Before
    fun setup() {
        loginRemoteDataSource = spy(LoginRemoteDataSourceImpl(loginApi))
    }

    @Test
    fun executeLogin() = runTest {
        val loginMap: HashMap<String, String> = hashMapOf("" to "")

        Mockito.`when`(loginApi.login(loginMap))
            .thenReturn(
                LoginDto(userName = "dummyUser", token = "dummyToken")
            )
        val result = loginRemoteDataSource.login(loginMap)

        Assert.assertEquals(result.javaClass, LoginDto::class.java)
    }
}