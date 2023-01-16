package com.example.simpleremotetask.features.medicine.data.remote.data_source

import com.example.simpleremotetask.features.medicine.data.remote.api.MedicinesApi
import com.example.simpleremotetask.features.medicine.data.remote.dto.DiseasesDto
import com.example.simpleremotetask.features.medicine.data.remote.dto.Medication
import com.example.simpleremotetask.features.medicine.data.remote.dto.Problem
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
class MedicineRemoteDataSourceImplTest {
    @Mock
    private lateinit var medicinesApi: MedicinesApi

    private lateinit var medicineRemoteDataSource: MedicineRemoteDataSource

    @Before
    fun setup() {
        medicineRemoteDataSource =
            Mockito.spy(MedicineRemoteDataSourceImpl(medicinesApi))
    }

    @Test
    fun getMedicines() = runTest {
        val medication = Medication(
            id = 1,
            dose = "dose",
            name = "name",
            strength = "strength"
        )
        val medication2 = Medication(
            id = 2,
            dose = "dose2",
            name = "name2",
            strength = "strength2"
        )
        val problem = Problem(id = "1", listOf(medication, medication2))
        val disease = DiseasesDto(listOf(problem, problem))

        Mockito.`when`(medicinesApi.getMedicines()).thenReturn(disease)

        val result = medicineRemoteDataSource.getMedicines()
        Assert.assertEquals(result, disease)
    }
}