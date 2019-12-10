package com.trella.map

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trella.common.GPSTracker
import com.trella.map.databinding.ActivityMapsBinding


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMapClickListener {

    private lateinit var mMap: GoogleMap
    private var marker: MarkerOptions = MarkerOptions()
    private lateinit var viewBinging: ActivityMapsBinding
    private val rxPermissions = RxPermissions(this)

    private var selectedLocation: com.elm.entities.location.Location =
        com.elm.entities.location.Location()
    private val gpsTracker = GPSTracker(this)
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinging = ActivityMapsBinding.inflate(layoutInflater)
        viewBinging.apply {

            //result back with a selected location on map
            btSave.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra("result", selectedLocation)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            //result back with no filtering location
            btReset.setOnClickListener {
                val resultIntent = Intent()
                resultIntent.putExtra("result", com.elm.entities.location.Location())
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
            backArrow.setOnClickListener {
                finish()
            }
        }
        setContentView(viewBinging.root)

        rxPermissions
            .request(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .subscribe {
                if (it) {
                    val mapFragment = supportFragmentManager
                        .findFragmentById(R.id.map) as SupportMapFragment
                    mapFragment.getMapAsync(this)
                } else
                    finish()
            }


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMapClickListener(this)
        mMap.uiSettings.isMyLocationButtonEnabled = false
        mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        mMap.isBuildingsEnabled = true
        mMap.isIndoorEnabled = true

        val loc: Location? = gpsTracker.location
        if (loc != null) {
            val latLng = LatLng(loc.latitude, loc.longitude)
            moveMarker(latLng)
            viewBinging.apply {
                myCurrentLocation.setOnClickListener {
                    moveMarker(latLng)
                }
            }
        }
        mMap.isMyLocationEnabled = false
    }

    override fun onMapClick(p0: LatLng?) {
        moveMarker(p0!!)
    }

    // function to move marker to no location and resetting map
    private fun moveMarker(location: LatLng) {
        selectedLocation.apply {
            lat = location.latitude
            lng = location.longitude
        }
        mMap.clear()
        marker.position(location)
        mMap.addMarker(marker)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 14f))
    }

    override fun onDestroy() {
        super.onDestroy()
        gpsTracker.stopUsingGPS()

    }
}
