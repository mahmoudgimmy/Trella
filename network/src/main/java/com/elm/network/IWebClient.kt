package com.elm.network


import com.elm.entities.shipment.Shipment

interface IWebClient {

    suspend fun getShipmentList(lng: Double?, lat: Double?): List<Shipment>

}