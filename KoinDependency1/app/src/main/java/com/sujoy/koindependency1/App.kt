package com.sujoy.koindependency1

import android.app.Application
import com.sujoy.koindependency1.core.di.coreModule
import com.sujoy.koindependency1.feature.di.featureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(coreModule, featureModule)
        }
    }
}