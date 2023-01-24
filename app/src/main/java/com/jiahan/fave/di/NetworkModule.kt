package com.jiahan.fave.di

import com.jiahan.fave.network.NetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideNetworkService(): NetworkService {
        return NetworkService.create()
    }

}

