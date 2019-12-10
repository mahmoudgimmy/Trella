package com.trella.shipments.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.trella.common.BaseFragment
import com.trella.common.bind
import com.trella.shipments.R
import com.trella.shipments.adapters.ShipmentsListAdapter
import com.trella.shipments.databinding.FragmentShipmentsListBinding
import com.trella.shipments.vm.ShipmentsViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ShipmentsFragment : BaseFragment<ShipmentsViewModel>() {

    // shared viewModel between fragment and it's hosting activity
    override val viewModel: ShipmentsViewModel by sharedViewModel()
    private lateinit var viewBinding: FragmentShipmentsListBinding
    private lateinit var shipmentsListAdapter: ShipmentsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = bind(R.layout.fragment_shipments_list, container)
        viewBinding.apply {

            shipmentsListAdapter = ShipmentsListAdapter()
            rvShipments.apply {
                val mLayout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                layoutManager = mLayout
                adapter = shipmentsListAdapter
            }

            viewModel.shipmentsList.observe(viewLifecycleOwner, Observer {
                if (it.isEmpty())
                    emptyShipments.visibility = View.VISIBLE
                else
                    emptyShipments.visibility = View.GONE

                shipmentsListAdapter.submitList(it)
            })

        }
        return viewBinding.root
    }
}