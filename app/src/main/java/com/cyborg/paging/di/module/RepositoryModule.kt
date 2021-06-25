package com.cyborg.paging.di.module

import com.cyborg.paging.data.executor.BaseSchedulerProvider
import com.cyborg.paging.data.local.LocalSource
import com.cyborg.paging.data.network.ApiService
import com.cyborg.paging.data.repository.GetPhotosRepository
import com.cyborg.paging.data.repository.GetPhotosRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideGetPhotosRepository(
        apiService: ApiService,
        localSource: LocalSource,
        schedulerProvider: BaseSchedulerProvider,
    ): GetPhotosRepository {
        return GetPhotosRepositoryImpl(apiService, localSource, schedulerProvider)
    }
}