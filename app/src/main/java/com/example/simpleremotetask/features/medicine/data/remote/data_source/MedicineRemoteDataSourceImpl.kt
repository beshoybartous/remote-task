package com.example.simpleremotetask.features.medicine.data.remote.data_source

import com.example.simpleremotetask.features.medicine.data.remote.api.MedicinesApi
import com.example.simpleremotetask.features.medicine.data.remote.dto.DiseasesDto

class MedicineRemoteDataSourceImpl(private val medicinesApi: MedicinesApi) :
    MedicineRemoteDataSource {
    override suspend fun getMedicines(): DiseasesDto =
        medicinesApi.getMedicines()

}