package com.trella

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.tbruyelle.rxpermissions2.RxPermissions
import com.trella.shipments.activities.ShipmentsActivity


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val rxPermissions = RxPermissions(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        Handler().postDelayed({

            rxPermissions
                .request(
                    Manifest.permission.INTERNET,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                .subscribe {
                    startActivity(Intent(this, ShipmentsActivity::class.java))
                    finish()
                }

        }, 3000)
    }
}
