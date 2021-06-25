package com.cyborg.paging

import com.cyborg.paging.di.component.DaggerApplicationComponent
import com.cyborg.paging.di.module.ApplicationModule
import com.cyborg.paging.di.module.DatabaseModule
import com.cyborg.paging.di.module.NetworkModule
import com.cyborg.paging.di.module.UseCaseModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class PagingApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerApplicationComponent.builder()
            .application(this)
            .applicationModule(ApplicationModule(applicationContext))
            .networkModule(NetworkModule())
            .databaseModule(DatabaseModule())
            .useCaseModule(UseCaseModule())
            .build()
}