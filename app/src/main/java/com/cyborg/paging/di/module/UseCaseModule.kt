package com.cyborg.paging.di.module

import com.cyborg.paging.data.executor.BaseSchedulerProvider
import com.cyborg.paging.data.executor.SchedulerProvider
import com.cyborg.paging.data.repository.GetPhotosRepository
import com.cyborg.paging.data.usecase.GetPhotosUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

    @Singleton
    @Provides
    fun provideGetPhotosUseCase(
        getPhotosRepository: GetPhotosRepository,
        schedulerProvider: BaseSchedulerProvider
    ): GetPhotosUseCase {
        return GetPhotosUseCase(getPhotosRepository, schedulerProvider)
    }
}