package com.trella.common

import androidx.fragment.app.Fragment


abstract class BaseFragment<VM : BaseViewModel> : Fragment() {
    abstract val viewModel: VM
}