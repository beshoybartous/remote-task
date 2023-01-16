package com.example.simpleremotetask.features.medicine.domain.use_case

import com.example.simpleremotetask.features.medicine.data.mapper.toMedicine
import com.example.simpleremotetask.features.medicine.data.remote.dto.Medication
import com.example.simpleremotetask.features.medicine.domain.repository.MedicineRepository
import com.example.simpleremotetask.network.NetworkResult
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
class GetMedicationsUseCaseTest {

    @Mock
    private lateinit var medicineRepository: MedicineRepository

    private lateinit var getMedicationsUseCase: GetMedicationsUseCase

    private val medication = Medication(
        id = 1,
        dose = "dose",
        name = "name",
        strength = "strength"
    )
    private val disease = "disease"

    private val medicinesList =
        listOf(medication.toMedicine(disease), medication.toMedicine(disease))

    @Before
    fun setup() {
        getMedicationsUseCase =
            Mockito.spy(
                GetMedicationsUseCase(
                    medicineRepository
                )
            )
    }

    @Test
    fun verifyGetMedicinesSuccess() = runTest {
        Mockito.`when`(
            medicineRepository.getMedications(
            )
        ).thenReturn(
            NetworkResult.Success(medicinesList)
        )

        val result = medicineRepository.getMedications()

        Assert.assertEquals(result.javaClass, NetworkResult.Success::class.java)
        Assert.assertEquals(result.data, medicinesList)
    }


    @Test
    fun verifyGetMedicinesError() = runTest {
        val errorMessage = "something went wrong"

        Mockito.`when`(
            medicineRepository.getMedications(
            )
        ).thenReturn(
            NetworkResult.Error(errorMessage)
        )

        val result = medicineRepository.getMedications()

        Assert.assertEquals(result.javaClass, NetworkResult.Error::class.java)
    }

}