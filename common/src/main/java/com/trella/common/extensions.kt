package com.trella.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

// an extension function to bind view using data binding in fragments
fun <T : ViewDataBinding> Fragment.bind(@LayoutRes layout: Int, container: ViewGroup?): T {
    val inflater = LayoutInflater.from(context)
    return DataBindingUtil.inflate(inflater, layout, container, false) as T
}

