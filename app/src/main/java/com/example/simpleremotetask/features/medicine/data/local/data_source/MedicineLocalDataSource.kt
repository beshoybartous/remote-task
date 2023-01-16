package com.example.simpleremotetask.features.medicine.data.local.data_source

import com.example.simpleremotetask.features.medicine.data.local.model.MedicineModel

interface MedicineLocalDataSource {
    suspend fun insertMedicines(medicines: MedicineModel)
    suspend fun getMedicine(id: Int): MedicineModel
}