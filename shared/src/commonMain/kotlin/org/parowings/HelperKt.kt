package org.parowings


import org.koin.core.context.startKoin
import org.parowings.di.appModule

fun initKoin() {
    // start Koin
    startKoin {
        modules(appModule())
    }
}