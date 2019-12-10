package com.trella.usecase.di

import com.google.gson.Gson
import com.trella.usecase.shipments.ShipmentsListUseCase
import org.koin.dsl.module


val useCaseModule = module {

    single {
        Gson()
    }

    single{
        ShipmentsListUseCase()
    }
}
