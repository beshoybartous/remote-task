package com.example.simpleremotetask.features.login.di

import com.example.simpleremotetask.features.login.data.remote.api.LoginApi
import com.example.simpleremotetask.features.login.data.remote.data_source.LoginRemoteDataSource
import com.example.simpleremotetask.features.login.data.remote.data_source.LoginRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LoginRemoteModule {

    @Provides
    @Singleton
    fun provideLoginApi(retrofit: Retrofit): LoginApi =
        retrofit.create(LoginApi::class.java)

    @Provides
    @Singleton
    fun provideLoginRemoteDataSource(
        loginApi: LoginApi
    ): LoginRemoteDataSource {
        return LoginRemoteDataSourceImpl(
            loginApi = loginApi
        )
    }
}