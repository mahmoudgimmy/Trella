package com.trella.shipments.di

import com.trella.shipments.vm.ShipmentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val shipmentsModule = module {
    viewModel {
        ShipmentsViewModel(get())
    }
}