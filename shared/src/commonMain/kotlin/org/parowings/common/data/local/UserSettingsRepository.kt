package org.parowings.common.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class UserSettingsRepository(private val dataStore: DataStore<Preferences>) {

    // Companion object to hold the key for our stored data.
    private companion object {
        // A key to store the UserSettings object as a single JSON string.
        val USER_SETTINGS_KEY = stringPreferencesKey("user_settings_json")
    }

    /**
     * A Flow that emits the current UserSettings.
     * It automatically updates when the data changes.
     */
    val userSettingsFlow: Flow<UserSettings> = dataStore.data
        .catch { exception ->
            // Handle potential errors, e.g., if the file is corrupted.
            // For now, we just emit an empty UserSettings.
            println("Error reading datastore preferences: $exception")
            emit(emptyPreferences())
        }
        .map { preferences ->
            // Get the JSON string from preferences using our key.
            val jsonString = preferences[USER_SETTINGS_KEY]
            if (jsonString != null) {
                // If the string exists, decode it back into a UserSettings object.
                try {
                    Json.decodeFromString<UserSettings>(jsonString)
                } catch (e: Exception) {
                    println("Error decoding UserSettings: $e")
                    UserSettings() // Return default on error
                }
            } else {
                // If it doesn't exist (e.g., first app launch), return a default object.
                UserSettings()
            }
        }

    /**
     * Saves the entire UserSettings object to DataStore.
     * This is a suspend function because DataStore operations are asynchronous.
     */
    suspend fun saveUserSettings(userSettings: UserSettings) {
        dataStore.edit { preferences ->
            // Encode the UserSettings object into a JSON string.
            val jsonString = Json.encodeToString(userSettings)
            // Save the string to our key.
            preferences[USER_SETTINGS_KEY] = jsonString
        }
    }

    /**
     * Clears all user settings from DataStore.
     */
    suspend fun clearUserSettings() {
        dataStore.edit { preferences ->
            preferences.remove(USER_SETTINGS_KEY)
        }
    }
}