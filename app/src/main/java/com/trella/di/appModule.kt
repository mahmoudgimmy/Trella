package com.trella.di

import com.tbruyelle.rxpermissions2.RxPermissions
import com.trella.destinations.ShipmentsDestination
import com.trella.shipments.navigation.IShipmentsNavigator
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<IShipmentsNavigator> { ShipmentsDestination() }
}