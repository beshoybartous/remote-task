package com.example.simpleremotetask.features.login.data.mapper

import com.example.simpleremotetask.features.login.data.remote.dto.LoginDto
import org.junit.Assert.assertEquals
import org.junit.Test

class LoginMapperTest {
    @Test
    fun getLoginModelFromLoginDto() {
        val dummyUserName = "dummyUserName"
        val dummyToken = "dummyToken"
        val loginDto = LoginDto(
            userName = dummyUserName,
            token = dummyToken
        )

        val login = loginDto.toLoginModel()
        assertEquals(login.userName, loginDto.userName)
        assertEquals(login.token, loginDto.token)
    }

}