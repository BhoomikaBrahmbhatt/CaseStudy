package com.android.demo.casestudy.di

import com.android.demo.casestudy.network.BaseApi
import com.android.demo.casestudy.network.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseApi(
        remoteDataSource: RemoteDataSource
    ): BaseApi {
        return remoteDataSource.buildApi(BaseApi::class.java)
    }
}