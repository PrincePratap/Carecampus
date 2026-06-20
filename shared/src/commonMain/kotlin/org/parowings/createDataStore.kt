package org.parowings

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

//typealias PrefsDataStore = DataStore<Preferences>
//fun createDataStore(producePath: () -> String): PrefsDataStore =
//    PreferenceDataStoreFactory.createWithPath(
//        produceFile = { producePath().toPath() }
//    )
//
//internal const val dataStoreFileName = "hai.preferences_pb"
//
//@Composable
//expect fun rememberDataStore(): PrefsDataStore

internal const val dataStoreFileName = "hai.preferences_pb"

// A shared typealias for easier use
typealias PrefsDataStore = DataStore<Preferences>

// The non-composable declaration that each platform will implement
expect fun createDataStore(context: Any? = null): PrefsDataStore