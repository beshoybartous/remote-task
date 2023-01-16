package com.example.simpleremotetask.features.medicine.di

import com.example.simpleremotetask.features.medicine.data.local.data_source.MedicineLocalDataSource
import com.example.simpleremotetask.features.medicine.data.remote.data_source.MedicineRemoteDataSource
import com.example.simpleremotetask.features.medicine.data.repository.MedicineRepositoryImpl
import com.example.simpleremotetask.features.medicine.domain.repository.MedicineRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MedicineRepositoryModule {

    @Provides
    @Singleton
    fun provideMedicineRepository(
        medicineRemoteDataSource: MedicineRemoteDataSource,
        medicineLocalDataSource: MedicineLocalDataSource
    ): MedicineRepository =
        MedicineRepositoryImpl(
            medicineRemoteDataSource,
            medicineLocalDataSource
        )

}