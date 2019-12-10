package com.trella.di

import com.trella.destinations.ShipmentsDestination
import com.trella.shipments.navigation.IShipmentsNavigator
import org.koin.dsl.module

val appModule = module {
    single<IShipmentsNavigator> { ShipmentsDestination() }
}