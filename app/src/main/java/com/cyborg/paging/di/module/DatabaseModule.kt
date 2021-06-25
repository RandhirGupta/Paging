package com.cyborg.paging.di.module

import android.content.Context
import com.cyborg.paging.data.local.LocalSource
import com.cyborg.paging.data.local.LocalSourceImpl
import com.cyborg.paging.data.local.PhotosDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    fun provideDatabase(context: Context): PhotosDatabase = PhotosDatabase.getInstance(context)

    @Singleton
    @Provides
    fun provideLocalSource(photosDatabase: PhotosDatabase): LocalSource =
        LocalSourceImpl(photosDatabase)
}