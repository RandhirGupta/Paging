package com.cyborg.paging.di.builder

import com.cyborg.paging.di.module.MainModule
import com.cyborg.paging.presentation.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainModule::class])
    fun contributeHomeActivity(): HomeActivity
}