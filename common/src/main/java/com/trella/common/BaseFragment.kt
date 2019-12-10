package com.trella.common

import androidx.fragment.app.Fragment
import com.elm.entities.interfaces.IActivityChanges
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext


abstract class BaseFragment<VM : BaseViewModel> : Fragment(), CoroutineScope {
    private val fragmentJob = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = CoroutineName("FRAG_NAME") + fragmentJob + Dispatchers.IO

    abstract val viewModel: VM

    open fun showLoading() {
        val host = activity
        if (host is IActivityChanges?) {
            host?.showLoading(true)
        }
    }

    open fun hideLoading() {
        val host = activity
        if (host is IActivityChanges?) {
            host?.showLoading(false)
        }
    }

    open fun showAlertDialog(title: String, message: String, type: Int) {
        val host = activity
        if (host is IActivityChanges?) {
            host?.showAlert(title, message, type)
        }
    }


    enum class AlertDialogType(val type: Int) {
        Error(1),
        NoInternet(2),
    }
}