package org.parowings

import android.app.Application
import org.parowings.di.appModule
import org.parowings.di.androidModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module



class ParoWingsApp : Application() {
//    val androidModule = module {
//        single { createDataStore ( androidContext() ) }
//
//    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ParoWingsApp)
            modules(appModule(), androidModule() )
        }
    }
}
