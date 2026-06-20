package org.parowings.di

import org.koin.dsl.module
import org.parowings.common.authentication.AuthRepository
import org.parowings.common.authentication.AuthRepositoryImpl
import org.parowings.common.authentication.AuthService
import org.parowings.common.authentication.AuthServiceImpl
import org.parowings.common.authentication.AuthViewModel
import org.parowings.common.data.local.UserSettingsRepository
import org.parowings.common.data.remote.createHttpClient
import org.parowings.common.util.provideDispatcher

fun appModule() = module {



    single {
        createHttpClient()
    }

    factory {
        provideDispatcher()
    }

    single<AuthService> {
        AuthServiceImpl(get())
    }

    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }

    factory {
        AuthViewModel(get())
    }

//    factory { PhonePeService(get()) }
//    single<PhonePeRepository> { PhonePeRepositoryImpl(get(), get(),get()) }
//    factory { PhonePeGaneshTheatreUseCase() }
//    factory { TicketPurchaseUseCase() }

}