android {

    defaultConfig {
        applicationId = "com.cyborg.paging"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        create("release")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("release")
        }
    }

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }
}

dependencies {
    implementation(PagingConfig.Libs.Kotlin.jvm)

    implementation(PagingConfig.Libs.Support.appCompat)
    implementation(PagingConfig.Libs.Support.design)
    implementation(PagingConfig.Libs.Support.constraintLayout)
    implementation(PagingConfig.Libs.Support.multiDex)
    implementation(PagingConfig.Libs.Support.annotations)
    implementation(PagingConfig.Libs.Support.materialDesign)

    implementation(PagingConfig.Libs.Rx.rxJava)
    implementation(PagingConfig.Libs.Rx.rxKotlin)
    implementation(PagingConfig.Libs.Rx.rxAndroid)

    implementation(PagingConfig.Libs.Arch.lifeCycle)
    implementation(PagingConfig.Libs.Arch.lifeCycleReactiveStream)
    implementation(PagingConfig.Libs.Arch.room)
    kapt(PagingConfig.Libs.Arch.roomCompiler)
    implementation(PagingConfig.Libs.Arch.roomRxJava)

    implementation(PagingConfig.Libs.Dagger.daggerAndroid)
    implementation(PagingConfig.Libs.Dagger.daggerAndroidSupport)

    implementation(PagingConfig.Libs.Misc.retrofit)
    implementation(PagingConfig.Libs.Misc.retrofitGSONConverter)
    implementation(PagingConfig.Libs.Misc.rxRetrofitAdapter)
    implementation(PagingConfig.Libs.Misc.okHttpInterceptor)
    implementation(PagingConfig.Libs.Misc.retrofitAdapter)
    implementation(PagingConfig.Libs.Misc.glide)
    implementation(PagingConfig.Libs.Misc.pagingRuntime)
    implementation(PagingConfig.Libs.Misc.pagingRxJava)

    kapt(PagingConfig.Libs.Dagger.daggerCompiler)
    kapt(PagingConfig.Libs.Dagger.daggerAndroidCompiler)
}