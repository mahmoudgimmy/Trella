package com.trella.shipments.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.elm.entities.location.Location
import com.elm.entities.shipment.Shipment
import com.trella.common.BaseViewModel
import com.trella.usecase.shipments.ShipmentsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShipmentsViewModel(private val shipmentsListUseCase: ShipmentsListUseCase) : BaseViewModel() {

    val shipmentsList = MutableLiveData<List<Shipment>>()

    fun getShipmentList(location: Location = Location()) {
        viewModelScope.launch(Dispatchers.IO) {
            showLoading(true)
            val result = shipmentsListUseCase(location)
            filteringResult(result, shipmentsList)

        }
    }
}