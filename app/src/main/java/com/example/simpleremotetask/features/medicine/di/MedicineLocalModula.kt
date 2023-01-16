package com.example.simpleremotetask.features.medicine.di

import com.example.simpleremotetask.features.medicine.data.local.data_source.MedicineLocalDataSource
import com.example.simpleremotetask.features.medicine.data.local.data_source.MedicineLocalDataSourceImpl
import com.example.simpleremotetask.features.medicine.data.local.database.MedicinesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MedicineLocalModula {

    @Provides
    @Singleton
    fun provideMedicineLocalDataSource(medicinesDatabase: MedicinesDatabase): MedicineLocalDataSource =
        MedicineLocalDataSourceImpl(medicinesDatabase.medicineDao())
}