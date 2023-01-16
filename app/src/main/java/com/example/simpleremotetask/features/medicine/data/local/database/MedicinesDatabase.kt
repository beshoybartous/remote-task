package com.example.simpleremotetask.features.medicine.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.simpleremotetask.features.medicine.data.local.dao.MedicinesDao
import com.example.simpleremotetask.features.medicine.data.local.model.MedicineModel

@Database(entities = [MedicineModel::class], version = 1)
abstract class MedicinesDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicinesDao
}