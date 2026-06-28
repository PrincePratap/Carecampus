package org.parowings.di

import org.koin.dsl.module
import org.parowings.common.adoption.AdoptionRepository
import org.parowings.common.adoption.AdoptionRepositoryImpl
import org.parowings.common.adoption.AdoptionService
import org.parowings.common.adoption.AdoptionServiceImpl
import org.parowings.common.adoption.AdoptionViewModel
import org.parowings.common.authentication.AuthRepository
import org.parowings.common.authentication.AuthRepositoryImpl
import org.parowings.common.authentication.AuthService
import org.parowings.common.authentication.AuthServiceImpl
import org.parowings.common.authentication.AuthViewModel
import org.parowings.common.data.local.UserSettingsRepository
import org.parowings.common.data.remote.createHttpClient
import org.parowings.common.util.provideDispatcher
import org.parowings.screens.adoption.petAdoption.PetAdoptionViewModel
import kotlin.coroutines.EmptyCoroutineContext.get

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

    single<AdoptionService> { AdoptionServiceImpl(get()) }

    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }

    single<AdoptionRepository> {
        AdoptionRepositoryImpl(
            dispatcher = get(),
            adoptionService = get()
        )
    }

    factory {
        AuthViewModel(get(), get())
    }

    factory {
        AdoptionViewModel(get())
        PetAdoptionViewModel(get())
    }

//    factory { PhonePeService(get()) }
//    single<PhonePeRepository> { PhonePeRepositoryImpl(get(), get(),get()) }
//    factory { PhonePeGaneshTheatreUseCase() }
//    factory { TicketPurchaseUseCase() }

}