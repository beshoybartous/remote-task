package com.example.simpleremotetask.features.login.data.remote.api

import com.example.simpleremotetask.features.login.data.remote.dto.LoginDto
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("003136bb-464b-439a-a4f9-d0c1efb87717")
    suspend fun login(@Body body: HashMap<String, String>): LoginDto
}