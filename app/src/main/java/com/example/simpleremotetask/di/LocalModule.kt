package com.example.simpleremotetask.di

import android.content.Context
import androidx.room.Room
import com.example.simpleremotetask.features.medicine.data.local.database.MedicinesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {
    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MedicinesDatabase =
        Room.databaseBuilder(
            context,
            MedicinesDatabase::class.java,
            "medicines.db"
        ).build()


}