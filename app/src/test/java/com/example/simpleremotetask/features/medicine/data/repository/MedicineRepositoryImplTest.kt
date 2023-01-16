package com.example.simpleremotetask.features.medicine.data.repository

import com.example.simpleremotetask.features.medicine.data.local.data_source.MedicineLocalDataSource
import com.example.simpleremotetask.features.medicine.data.mapper.toMedicine
import com.example.simpleremotetask.features.medicine.data.mapper.toMedicineModel
import com.example.simpleremotetask.features.medicine.data.remote.data_source.MedicineRemoteDataSource
import com.example.simpleremotetask.features.medicine.data.remote.dto.DiseasesDto
import com.example.simpleremotetask.features.medicine.data.remote.dto.Medication
import com.example.simpleremotetask.features.medicine.data.remote.dto.Problem
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
import java.net.SocketException


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class MedicineRepositoryImplTest {

    @Mock
    private lateinit var medicineLocalDataSource: MedicineLocalDataSource

    @Mock
    private lateinit var medicineRemoteDataSource: MedicineRemoteDataSource

    private lateinit var medicineRepository: MedicineRepository

    private val medication = Medication(
        id = 1,
        dose = "dose",
        name = "name",
        strength = "strength"
    )
    private val disease = "disease"

    private val problem = Problem(id = disease, listOf(medication, medication))
    private val diseaseDto = DiseasesDto(listOf(problem))

    private val medicinesList =
        listOf(medication.toMedicine(disease), medication.toMedicine(disease))

    @Before
    fun setup() {
        medicineRepository =
            Mockito.spy(
                MedicineRepositoryImpl(
                    medicineRemoteDataSource,
                    medicineLocalDataSource
                )
            )
    }

    @Test
    fun verifyGetMedicinesSuccess() = runTest {
        Mockito.`when`(
            medicineRemoteDataSource.getMedicines(
            )
        ).thenReturn(
            diseaseDto
        )

        val result = medicineRepository.getMedications()

        Assert.assertEquals(result.javaClass, NetworkResult.Success::class.java)
    }


    @Test
    fun verifyGetMedicinesError() = runTest {
        Mockito.`when`(
            medicineRemoteDataSource.getMedicines(
            )
        ).thenAnswer {
            throw SocketException()
        }

        val result = medicineRepository.getMedications()

        Assert.assertEquals(result.javaClass, NetworkResult.Error::class.java)
        Mockito.reset(medicineRemoteDataSource)

    }

    @Test
    fun verifyMedicinesCached() = runTest {
        val medicineModel = medication.toMedicineModel("disease")
        medicineRepository.cacheMedicines("disease", medication)
        Mockito.verify(medicineLocalDataSource).insertMedicines(medicineModel)
    }

    @Test
    fun filterMedicines() = runTest {
        val result = medicineRepository.filterMedicines(diseaseDto)
        Assert.assertEquals(result, medicinesList)
    }

}