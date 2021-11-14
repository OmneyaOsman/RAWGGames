package com.omni.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class GenerePreferencesSourceImpTest : CoroutineTest() {

    private lateinit var preferencesScope: CoroutineScope
    private lateinit var dataStore: DataStore<Preferences>
    private val repository: GenerePreferencesSourceImp by lazy {
        GenerePreferencesSourceImp(
            dataStore
        )
    }

    @Before
    fun createDataStore() {
        preferencesScope = CoroutineScope(testDispatcher + Job())
        dataStore = PreferenceDataStoreFactory.create(scope = preferencesScope) {
            ApplicationProvider.getApplicationContext<Context>()
                .preferencesDataStoreFile(
                    "test-preferences-file"
                )
        }
    }

    @After
    fun removeDatastore() {
        File(
            ApplicationProvider.getApplicationContext<Context>().filesDir,
            "test-preferences-file"
        ).deleteRecursively()

        preferencesScope.cancel()
    }


    @Test
    fun whenSetGenereValueThenGenereValueChanged() = coTest {
        repository.setGenere("5")
        val genere =repository.getGenere().first()
            assertEquals("5", genere)
    }


}