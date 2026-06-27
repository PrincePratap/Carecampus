package org.parowings

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile

 actual fun createDataStore(context: Any? ): PrefsDataStore {
    val androidContext = context as? Context
        ?: throw IllegalArgumentException("Context is required to create DataStore on Android")

    return PreferenceDataStoreFactory.create(
        produceFile = { androidContext.preferencesDataStoreFile(dataStoreFileName) }
    )
}
