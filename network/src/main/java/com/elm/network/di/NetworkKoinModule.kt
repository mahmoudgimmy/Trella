package com.elm.network.di

import com.elm.network.BaseApiClient
import com.elm.network.IWebClient
import com.elm.network.WebClient
import com.elm.network.endpoints.TrellaEndPoints
import com.elm.network.interceptors.AuthenticationInterceptor
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

const val NOVATIONBUILDER = "NovationBuilder"
const val NOVATIONOKHTTPCLIENT = "NovationOkhttpClient"
const val HTTPLOGGERINTERCEPTOR = "HttpLogger"
const val AUTHENTECATIONINTERCEPTOR = "AuthenInterceptor"
const val BASEURL = "https://case-api.trella.app/"


val networkModule = module {
    single(named(NOVATIONBUILDER)) {
        BaseApiClient.createRetroFitBuilder(
            get(named(NOVATIONOKHTTPCLIENT)),
            BASEURL
        )
    }
    single(named(NOVATIONOKHTTPCLIENT)) {
        BaseApiClient.createOkHttpClient(
            get(named(HTTPLOGGERINTERCEPTOR))
            , get(named(AUTHENTECATIONINTERCEPTOR))
        )
    }



    single<TrellaEndPoints> {
        val rfb: Retrofit.Builder = get(named(NOVATIONBUILDER))
        rfb.build().create(TrellaEndPoints::class.java)
    }

    single<IWebClient> {
        WebClient()
    }

    single(named((HTTPLOGGERINTERCEPTOR))) {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
    single(named(AUTHENTECATIONINTERCEPTOR)) {
        AuthenticationInterceptor()
    }

}
