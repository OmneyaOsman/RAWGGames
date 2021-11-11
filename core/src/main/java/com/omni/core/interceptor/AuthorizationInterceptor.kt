package com.omni.core.interceptor

import com.omni.core.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.request().let {
            it.url.newBuilder()
                .addQueryParameter("app_key", BuildConfig.API_KEY).build().let { url ->
                    it.newBuilder().url(url).build()
                }
        }.run {
            chain.proceed(this)
        }

}