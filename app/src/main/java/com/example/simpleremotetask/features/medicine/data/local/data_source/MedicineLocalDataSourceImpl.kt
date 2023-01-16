package com.example.simpleremotetask.features.medicine.data.local.data_source

import com.example.simpleremotetask.features.medicine.data.local.dao.MedicinesDao
import com.example.simpleremotetask.features.medicine.data.local.model.MedicineModel

class MedicineLocalDataSourceImpl(private val medicinesDao: MedicinesDao) :
    MedicineLocalDataSource {
    override suspend fun insertMedicines(medicines: MedicineModel) {
        medicinesDao.insertMedicines(medicines = medicines)
    }

    override suspend fun getMedicine(id: Int): MedicineModel =
        medicinesDao.getMedicine(id)

}