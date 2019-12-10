package com.elm.network.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.KoinComponent

// interceptor for http request
class AuthenticationInterceptor : Interceptor, KoinComponent {

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "mahmoud.gamal2791996@yahoo.com")
            .build()
        return chain.proceed(newRequest)
    }
}