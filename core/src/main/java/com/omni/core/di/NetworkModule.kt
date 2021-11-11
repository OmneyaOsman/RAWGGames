package com.omni.core.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.omni.core.BuildConfig
import com.omni.core.di.NetworkModuleConstants.RETROFIT_TIMEOUT
import com.omni.core.interceptor.AuthorizationInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { AuthorizationInterceptor() }
    single { provideOkHttpClient(get(), get()) }
    single { provideGson() }
    single { provideConnectivityManager(androidContext()) }
    single { provideGsonConverterFactory(get()) }
    single { provideRetrofitBuilder(get(), get()) }
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor { logMessage ->
        Timber.e(logMessage)
    }.apply {
        level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }


fun provideOkHttpClient(
    interceptor: HttpLoggingInterceptor,
    authorizationInterceptor: AuthorizationInterceptor
): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(authorizationInterceptor)
        .connectTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(RETROFIT_TIMEOUT, TimeUnit.SECONDS)
        .addNetworkInterceptor(interceptor)
        .build()

fun provideGson(): Gson =
    GsonBuilder().apply {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        setDateFormat("yyyy-MM-dd HH:mm:ss")
        setPrettyPrinting()
    }.create()

fun provideRetrofitBuilder(
    client: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory
): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(gsonConverterFactory)
        .build()


fun provideGsonConverterFactory(
    gson: Gson
): GsonConverterFactory = GsonConverterFactory.create(gson)

fun provideConnectivityManager(context: Context): ConnectivityManager =
    context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

object NetworkModuleConstants {
    const val RETROFIT_TIMEOUT = 10L
}