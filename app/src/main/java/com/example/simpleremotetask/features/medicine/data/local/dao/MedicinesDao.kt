package com.example.simpleremotetask.features.medicine.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.simpleremotetask.features.medicine.data.local.model.MedicineModel

@Dao
interface MedicinesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicines(medicines: MedicineModel)

    @Query("SELECT * FROM ${MedicineModel.TABLE_NAME} WHERE id=:id")
    suspend fun getMedicine(id: Int): MedicineModel


}