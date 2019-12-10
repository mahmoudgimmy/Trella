package com.trella.shipments.navigation

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

// a navigation interface used by app module(Destinations)
interface IShipmentsNavigator {
    fun toMapScreen(activity: Activity, requestCode: Int)
}