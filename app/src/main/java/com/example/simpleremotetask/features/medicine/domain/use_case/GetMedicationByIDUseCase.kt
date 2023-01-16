package com.example.simpleremotetask.features.medicine.domain.use_case

import com.example.simpleremotetask.features.medicine.domain.repository.MedicineRepository
import javax.inject.Inject

class GetMedicationByIDUseCase @Inject constructor(
    private val medicineRepository: MedicineRepository
    ) {
    suspend operator fun invoke(id: Int) =
        medicineRepository.getMedicationById(id)
}