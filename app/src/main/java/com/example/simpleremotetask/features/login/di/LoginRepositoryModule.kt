package com.example.simpleremotetask.features.login.di

import com.example.simpleremotetask.features.login.data.remote.data_source.LoginRemoteDataSource
import com.example.simpleremotetask.features.login.data.repository.LoginRepositoryImpl
import com.example.simpleremotetask.features.login.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginRepositoryModule {

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginRemoteDataSource: LoginRemoteDataSource,
    ): LoginRepository =
        LoginRepositoryImpl(loginRemoteDataSource)
}