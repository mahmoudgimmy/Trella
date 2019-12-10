package com.elm.network.endpoints

import com.elm.entities.shipment.Shipment
import retrofit2.http.GET
import retrofit2.http.Query

// interface which defines app endpoints
internal interface TrellaEndPoints {

    @GET("marketplace")
    suspend fun getShipmentList(@Query("lng") lng: Double?, @Query("lat") lat: Double?): List<Shipment>

}