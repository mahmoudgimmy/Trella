package com.trella.destinations

import android.app.Activity
import android.content.Intent
import androidx.core.app.ActivityCompat
import com.trella.map.MapsActivity
import com.trella.shipments.navigation.IShipmentsNavigator

class ShipmentsDestination : IShipmentsNavigator {
    override fun toMapScreen(activity: Activity, requestCode: Int) {
        ActivityCompat.startActivityForResult(
            activity,
            Intent(activity, MapsActivity::class.java),
            requestCode,
            null
        )
    }
}