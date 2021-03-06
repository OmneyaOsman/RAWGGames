package com.omni.data.remote.interceptor

import com.omni.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        chain.request().let {
            it.url.newBuilder()
                .addQueryParameter("key", BuildConfig.API_KEY).build().let { url ->
                    it.newBuilder().url(url).build()
                }
        }.run {
            chain.proceed(this)
        }

}