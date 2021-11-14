package com.omni.data.local.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.omni.domain.repository.GenerePreferencesSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException

const val GENERES_PREFERENCES = "generes.preferences_pb"

class GenerePreferencesSourceImp(private val dataStore: DataStore<Preferences>) :
    GenerePreferencesSource {
    override suspend fun setGenere(genereId: String?) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SELECTED_GENERE_VALUE] =
                genereId ?: ""
        }
    }

    override fun getGenere(): Flow<String>  = dataStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Timber.e(
                    "Error reading selected_expiring_policy_day_value preferences. $exception"
                )
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[PreferencesKeys.SELECTED_GENERE_VALUE]
                ?: ""
        }

    private object PreferencesKeys {
        //        val IS_EXPIRING_POLICY_REMINDER_TRIGGERED =
//            booleanPreferencesKey("is_expiring_policy_reminder_triggered")
        val SELECTED_GENERE_VALUE =
            stringPreferencesKey("selected_genere_value")
    }
}