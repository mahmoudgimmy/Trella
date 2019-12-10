package com.trella.usecase.shipments

import com.elm.entities.location.Location
import com.elm.entities.shipment.Shipment
import com.trella.usecase.GeneralUseCase

//use case of shipment list
class ShipmentsListUseCase :
    GeneralUseCase<Location, Shipment>() {
    override suspend fun execute(parameters: Location): List<Shipment> {
        return client.getShipmentList(parameters.lng, parameters.lat)
    }
}