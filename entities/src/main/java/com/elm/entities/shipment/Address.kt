package com.elm.entities.shipment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Address {
    @SerializedName("order")
    @Expose
    var order: Int? = null
    @SerializedName("key")
    @Expose
    var key: String? = null
    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null
    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}