package com.example.simpleremotetask.features.medicine.data.local.data_source

import com.example.simpleremotetask.features.medicine.data.local.dao.MedicinesDao
import com.example.simpleremotetask.features.medicine.data.local.model.MedicineModel
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
class MedicineLocalDataSourceImplTest {
    @Mock
    private lateinit var medicinesDao: MedicinesDao

    private lateinit var medicineLocalDataSource: MedicineLocalDataSource

    @Before
    fun setup() {
        medicineLocalDataSource =
            Mockito.spy(MedicineLocalDataSourceImpl(medicinesDao))
    }

    @Test
    fun insertMedicine() = runTest {

        val medicineModel = MedicineModel(
            id = 1, diseaseName = "", medicineName
            = "", medicineDose = "", medicineStrength = ""
        )

        medicineLocalDataSource.insertMedicines(medicineModel)

        Mockito.verify(medicinesDao).insertMedicines(medicineModel)
    }

    @Test
    fun getMedicineById() = runTest {
        val medicineModel = MedicineModel(
            id = 1, diseaseName = "", medicineName
            = "", medicineDose = "", medicineStrength = ""
        )
        Mockito.`when`(medicinesDao.getMedicine(1)).thenReturn(medicineModel)

        val result = medicineLocalDataSource.getMedicine(medicineModel.id)

        Assert.assertEquals(result.id, medicineModel.id)
    }

}