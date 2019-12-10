package com.elm.entities.shipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Shipment {
    @SerializedName("key")
    @Expose
    var key: String? = null

    @SerializedName("numberOfBids")
    @Expose
    var numberOfBids: Int? = null

    @SerializedName("commodity")
    @Expose
    var commodity: String? = null

    @SerializedName("vehicleType")
    @Expose
    var vehicleType: String? = null

    @SerializedName("price")
    @Expose
    var price: Double? = null

    @SerializedName("addresses")
    @Expose
    var addresses: List<Address>? = null
}