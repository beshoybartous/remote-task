package com.example.simpleremotetask.features.medicine.di

import com.example.simpleremotetask.features.medicine.data.remote.api.MedicinesApi
import com.example.simpleremotetask.features.medicine.data.remote.data_source.MedicineRemoteDataSource
import com.example.simpleremotetask.features.medicine.data.remote.data_source.MedicineRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MedicineRemoteModule {

    @Provides
    @Singleton
    fun provideMedicineApi(retrofit: Retrofit): MedicinesApi =
        retrofit.create(MedicinesApi::class.java)

    @Provides
    @Singleton
    fun provideMedicineRemoteDataSource(medicinesApi: MedicinesApi)
            : MedicineRemoteDataSource =
        MedicineRemoteDataSourceImpl(medicinesApi)
}