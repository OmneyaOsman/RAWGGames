package com.omni.data.di

import com.omni.data.local.db.RawgGamesDatabase
import com.omni.data.mapper.GameModelToGameEntityMapper
import com.omni.data.mapper.generes.GenereModelToGenereEntityMapper
import com.omni.data.remote.api.GamesAPI
import com.omni.data.remote.api.GeneresAPI
import com.omni.data.repository.GeneresRepositoryImp
import com.omni.data.repository.RAWgGamesRepositoryImp
import com.omni.domain.repository.GeneresRepository
import com.omni.domain.repository.RAWGGamesRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val generesRepositoryModule = module {

    factory { provideGeneresAPI(get()) }
    single { provideGenereModelToGenereEntityMapper() }
    single { provideRAWgGamesRepositoryImp(get(), get()) }
}

fun provideGenereModelToGenereEntityMapper() = GenereModelToGenereEntityMapper()
fun provideGeneresAPI(retrofit: Retrofit): GeneresAPI = retrofit.create(GeneresAPI::class.java)
fun provideRAWgGamesRepositoryImp(
    api: GeneresAPI, mapper: GenereModelToGenereEntityMapper
): GeneresRepository = GeneresRepositoryImp(api, mapper)
