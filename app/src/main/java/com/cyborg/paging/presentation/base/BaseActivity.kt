package com.cyborg.paging.presentation.base

import android.app.Activity
import android.os.Bundle
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity(), HasActivityInjector {

    @Inject
    lateinit var appCompatActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return appCompatActivityInjector
    }
}