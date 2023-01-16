package com.example.simpleremotetask.features.medicine.data.remote.api

import com.example.simpleremotetask.features.medicine.data.remote.dto.DiseasesDto
import retrofit2.http.GET

interface MedicinesApi {

    @GET("4e9cd04e-d0d4-471a-b4be-e03488e7d767")
    suspend fun getMedicines(): DiseasesDto
}