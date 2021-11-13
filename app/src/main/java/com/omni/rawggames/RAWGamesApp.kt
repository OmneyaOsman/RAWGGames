package com.omni.rawggames

import android.app.Application
import com.omni.data.di.networkModule
import com.omni.feature_games_list.di.gamesListFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class RAWGamesApp : Application() {


    override fun onCreate() {
        super.onCreate()
        setupTimber()
        setUpKoin()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement) =
                    "${super.createStackElementTag(element)}:${element.lineNumber}"
            })
        }
    }

    private fun setUpKoin() {
        startKoin {
            androidContext(this@RAWGamesApp)
            modules(
                listOf(
                    networkModule,
                    gamesListFeatureModule,
                )
            )
        }
    }
}