package com.trella

import androidx.multidex.MultiDexApplication
import com.elm.network.di.networkModule
import com.trella.di.appModule
import com.trella.shipments.di.shipmentsModule
import com.trella.usecase.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MainApp : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        // initializing koin
        startKoin {
            // to provide app context in all modules
            androidContext(this@MainApp)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    useCaseModule,
                    shipmentsModule
                )
            )

        }

    }


}
