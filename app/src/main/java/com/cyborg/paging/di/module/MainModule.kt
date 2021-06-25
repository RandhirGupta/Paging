package com.cyborg.paging.di.module

import androidx.lifecycle.ViewModel
import com.cyborg.paging.di.ViewModelKey
import com.cyborg.paging.presentation.home.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeActivityViewModel(homeViewModel: HomeViewModel): ViewModel
}