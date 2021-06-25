package com.cyborg.paging.di.module

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
        apiService: ApiService
    ): GetPhotosRepository {
        return GetPhotosRepositoryImpl(apiService)
    }
}