package com.elm.network

import com.elm.entities.shipment.Shipment
import com.elm.network.endpoints.TrellaEndPoints
import org.koin.core.KoinComponent
import org.koin.core.inject

 class WebClient : IWebClient, KoinComponent {
    private val endPoint: TrellaEndPoints by inject()
    override suspend fun getShipmentList(lng: Double?, lat: Double?): List<Shipment> {
        return endPoint.getShipmentList(lng,lat)
    }


}