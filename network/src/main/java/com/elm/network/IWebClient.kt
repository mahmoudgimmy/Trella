package com.elm.network


import com.elm.entities.shipment.Shipment

// interface which defines functions used by web client
interface IWebClient {

    suspend fun getShipmentList(lng: Double?, lat: Double?): List<Shipment>

}