package org.parowings.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.parowings.auth.AndroidAuthViewModel
import org.parowings.auth.GoogleAuthManager
import org.parowings.common.data.local.UserSettingsRepository

fun androidModule() = module {

    single<DataStore<Preferences>> {
        PreferenceDataStoreFactory.create(
            scope = CoroutineScope(
                Dispatchers.IO + SupervisorJob()
            ),
            produceFile = {
                androidContext().preferencesDataStoreFile("user_prefs")
            }
        )
    }

    single {
        UserSettingsRepository(get())
    }

    single {
        UserSettingsRepository(get())
    }

    single {
        // Pass the raw OAuth Web Client ID directly as a string literal
        val webClientId = "622596442669-sns1dhejiqv4c95cblvn7j5fh72vogcj.apps.googleusercontent.com"

        GoogleAuthManager(webClientId)
    }

    single {
        AndroidAuthViewModel(
            get(),
            get(),
            get()
        )
    }
}