package com.omni.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.google.gson.Gson
import com.omni.data.local.datastore.GENERES_PREFERENCES
import com.omni.data.local.datastore.GenerePreferencesSourceImp
import com.omni.data.local.db.RawgGamesDatabase
import com.omni.data.mapper.GameModelToGameEntityMapper
import com.omni.data.mapper.generes.GenereModelToGenereEntityMapper
import com.omni.data.remote.api.GamesAPI
import com.omni.data.remote.api.GeneresAPI
import com.omni.data.repository.GeneresRepositoryImp
import com.omni.data.repository.RAWgGamesRepositoryImp
import com.omni.domain.repository.GenerePreferencesSource
import com.omni.domain.repository.GeneresRepository
import com.omni.domain.repository.RAWGGamesRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import java.io.File

val generesRepositoryModule = module {

    factory { provideGeneresAPI(get()) }
    single { provideGenereModelToGenereEntityMapper() }
    single { provideGeneresDataSTore(get()) }

    single { provideGeneresRepositoryImp(get(), get(), get()) }
    single { provideGenerePreferencesSourceImp(get()) }
}

fun provideGenereModelToGenereEntityMapper() = GenereModelToGenereEntityMapper()
fun provideGeneresAPI(retrofit: Retrofit): GeneresAPI = retrofit.create(GeneresAPI::class.java)
fun provideGeneresRepositoryImp(
    api: GeneresAPI, mapper: GenereModelToGenereEntityMapper, gson: Gson
): GeneresRepository = GeneresRepositoryImp(api, mapper, gson)


fun provideGeneresDataSTore(context: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create(
        produceFile = { File(context.filesDir, GENERES_PREFERENCES) },
    )

fun provideGenerePreferencesSourceImp(dataStore: DataStore<Preferences>): GenerePreferencesSource =
    GenerePreferencesSourceImp(dataStore)