package com.trella.shipments.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.elm.entities.interfaces.IActivityChanges
import com.elm.entities.location.Location
import com.trella.common.BaseActivity
import com.trella.shipments.R
import com.trella.shipments.databinding.ActivityShipmentsBinding
import com.trella.shipments.navigation.IShipmentsNavigator
import com.trella.shipments.vm.ShipmentsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ShipmentsActivity : BaseActivity<ShipmentsViewModel>(), IActivityChanges {
    override val viewModel: ShipmentsViewModel by viewModel()
    private val shipmentsDestination: IShipmentsNavigator by inject()
    private lateinit var viewBinding: ActivityShipmentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityShipmentsBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)
        // calling shipment list api
        viewModel.getShipmentList()
    }

    override fun showLoading(show: Boolean) {
        viewBinding.apply {
            if (show)
                mainProgressBar.visibility = View.VISIBLE
            else
                mainProgressBar.visibility = View.GONE
        }
    }

    override fun showAlert(title: String, message: String, type: Int) {
        showAlertDialog(title, message, type)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.shipments_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.ic_filter -> {
                shipmentsDestination.toMapScreen(this, MAP_FILTER_REQUEST_CODE)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val MAP_FILTER_REQUEST_CODE = 1010
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            val location = data?.getSerializableExtra("result") as Location
            if (requestCode == MAP_FILTER_REQUEST_CODE) {
                viewModel.getShipmentList(location)
            }
        }
    }
}
