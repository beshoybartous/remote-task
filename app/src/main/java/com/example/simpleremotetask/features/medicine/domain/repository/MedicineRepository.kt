package com.example.simpleremotetask.features.medicine.domain.repository

import com.example.simpleremotetask.features.medicine.data.remote.dto.DiseasesDto
import com.example.simpleremotetask.features.medicine.data.remote.dto.Medication
import com.example.simpleremotetask.features.medicine.domain.model.Medicine
import com.example.simpleremotetask.network.NetworkResult

interface MedicineRepository {
    suspend fun getMedications(): NetworkResult<List<Medicine>>
    suspend fun getMedicationById(id: Int): Medicine
    suspend fun cacheMedicines(id: String?, medicine: Medication?)
    suspend fun filterMedicines(result: DiseasesDto): MutableList<Medicine>
}
