package com.example.simpleremotetask.features.medicine.domain.use_case

import com.example.simpleremotetask.features.medicine.domain.model.Medicine
import com.example.simpleremotetask.features.medicine.domain.repository.MedicineRepository
import com.example.simpleremotetask.network.NetworkResult
import javax.inject.Inject

class GetMedicationsUseCase @Inject constructor(
    private val medicineRepository: MedicineRepository
) {
    suspend operator fun invoke(): NetworkResult<List<Medicine>> = medicineRepository.getMedications()
}

