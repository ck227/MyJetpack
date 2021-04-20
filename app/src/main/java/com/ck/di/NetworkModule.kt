package com.ck.di

import com.ck.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 *
 * @author ck
 * @date 2020/12/17
 */

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHomeService(): ApiService {
        return ApiService.create()
    }
}