package com.example.simpleremotetask.features.medicine.data.remote.data_source

import com.example.simpleremotetask.features.medicine.data.remote.dto.DiseasesDto

interface MedicineRemoteDataSource {
    suspend fun getMedicines(): DiseasesDto
}