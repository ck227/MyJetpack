package com.ck.di

import com.ck.api.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton


/**
 *
 * @author ck
 * @date 2020/12/17
 */

@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideHomeService(): HomeService {
        return HomeService.create()
    }
}