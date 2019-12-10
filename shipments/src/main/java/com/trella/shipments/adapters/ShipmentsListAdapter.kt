package com.trella.shipments.adapters

import android.text.Html
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.bold
import androidx.core.text.toHtml
import androidx.databinding.DataBindingUtil.inflate
import androidx.recyclerview.widget.RecyclerView
import com.elm.entities.shipment.Shipment
import com.trella.shipments.R
import com.trella.shipments.databinding.ShipmentItemBinding

class ShipmentsListAdapter : RecyclerView.Adapter<ShipmentViewHolder>() {
    private val list: ArrayList<Shipment> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipmentViewHolder {
        return ShipmentViewHolder(
            inflate(
                LayoutInflater.from(parent.context),
                R.layout.shipment_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ShipmentViewHolder, position: Int) {
        holder.setDate(list[position])
    }

    fun submitList(newList: List<Shipment>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}

class ShipmentViewHolder(private val view: ShipmentItemBinding) :
    RecyclerView.ViewHolder(view.root) {
    private var styledText = SpannableStringBuilder()

    fun setDate(shipment: Shipment) {
        view.apply {
            packingIn.text = shipment.addresses?.get(0)?.name ?: ""
            packingOut.text = shipment.addresses?.get(1)?.name ?: ""
            vehicleType.text = shipment.vehicleType

            price.text = Html.fromHtml(styledText.bold { append(shipment.price.toString()) }
                .append(" / شحنة").toHtml())
            styledText.clear()

            goods.text = Html.fromHtml(styledText.bold { append(shipment.numberOfBids.toString() + " " + "طن ") }
                .append("  ${shipment.commodity}").toHtml())
            styledText.clear()

        }

    }

}