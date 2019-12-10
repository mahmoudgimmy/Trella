package com.trella.shipments.navigation

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

interface IShipmentsNavigator {
    fun toMapScreen(activity: Activity, requestCode: Int)
}