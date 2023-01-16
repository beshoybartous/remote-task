package com.example.simpleremotetask.features.medicine.domain.use_case

import com.example.simpleremotetask.features.medicine.domain.model.Medicine
import com.example.simpleremotetask.features.medicine.domain.repository.MedicineRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class GetMedicationByIDUseCaseTest {

    @Mock
    private lateinit var medicineRepository: MedicineRepository

    private lateinit var getMedicationByIDUseCase: GetMedicationByIDUseCase

    @Before
    fun setup() {
        getMedicationByIDUseCase =
            Mockito.spy(
                GetMedicationByIDUseCase(
                    medicineRepository
                )
            )
    }

    @Test
    fun getMedicineById() = runTest {
        val medicineModel = Medicine(
            id = 1,
            diseaseName = "diseaseName",
            medicineName = "medicineName",
            medicineDose = "medicineDose",
            medicineStrength = "medicineStrength"
        )
        Mockito.`when`(
            medicineRepository.getMedicationById(medicineModel.id)
        ).thenReturn(medicineModel)

        val result = getMedicationByIDUseCase(medicineModel.id)

        Assert.assertEquals(result, medicineModel)
    }
}