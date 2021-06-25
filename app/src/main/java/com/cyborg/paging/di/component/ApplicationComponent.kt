package com.cyborg.paging.di.component

import android.app.Application
import com.cyborg.paging.PagingApplication
import com.cyborg.paging.di.builder.ActivityBuilder
import com.cyborg.paging.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        UseCaseModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityBuilder::class]
)
interface ApplicationComponent : AndroidInjector<PagingApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun networkModule(networkModule: NetworkModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
        fun useCaseModule(useCaseModule: UseCaseModule): Builder
        fun repositoryModule(repositoryModule: RepositoryModule): Builder
        fun build(): ApplicationComponent
    }

    override fun inject(instance: PagingApplication)
}