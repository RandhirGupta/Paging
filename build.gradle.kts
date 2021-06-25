import com.android.build.gradle.BaseExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(PagingConfig.Plugins.androidPlugin)
        classpath(PagingConfig.Plugins.kotlinPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }

    if ((group as String).isNotEmpty()) {
        conFigureAndroid()
    }
}


task("clean") {
    delete(rootProject.buildDir)
}

fun Project.conFigureAndroid() {
    apply(plugin = "com.android.application")
    apply(plugin = "kotlin-android")
    apply(plugin = "kotlin-android-extensions")
    apply(plugin = "kotlin-kapt")

    configure<BaseExtension> {
        compileSdkVersion(PagingConfig.SdkVersion.compileSdkVersion)

        defaultConfig {
            minSdkVersion(PagingConfig.SdkVersion.minSdkVersion)
            targetSdkVersion(PagingConfig.SdkVersion.targetSdkVersion)
            versionCode = 1
            versionName = PagingConfig.version
        }
    }
}