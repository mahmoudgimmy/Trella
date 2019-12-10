package com.trella.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.andrognito.flashbar.Flashbar
import com.andrognito.flashbar.anim.FlashAnim
import com.elm.entities.ErrorMessageException
import com.elm.entities.NoInterNetConnectionException
import com.elm.entities.UnAuthorizedException
import com.elm.entities.interfaces.IActivityChanges

abstract class BaseActivity<VM: BaseViewModel> : AppCompatActivity() {
    abstract val viewModel:VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loading.observe(this, Observer {
            when(it){
                true -> showLoading()
                false -> hideLoading()
            }
        })
        viewModel.messages.observe(this, Observer {
            when (it.second) {
                is UnAuthorizedException -> showAlertDialog("Authorization",it.first,
                    BaseFragment.AlertDialogType.NoInternet.type)
                is NoInterNetConnectionException -> showAlertDialog("No Internet Connection",it.first,
                    BaseFragment.AlertDialogType.NoInternet.type)
                is ErrorMessageException -> showAlertDialog("Error!",it.first,
                    BaseFragment.AlertDialogType.Error.type)
            }
        })
    }
    private fun showLoading() {
        if (this is IActivityChanges) {
            this.showLoading(true)
        }
    }

    /**
     * Hide Loading indicator.
     */
    private fun hideLoading() {
        if (this is IActivityChanges) {
            this.showLoading(false)
        }
    }

    open fun showAlertDialog(title: String, message: String, type: Int) {

        Flashbar.Builder(this)
            .gravity(Flashbar.Gravity.TOP)
            .title(title)
            .message(message)
            .icon(
                when (type) {
                    BaseFragment.AlertDialogType.NoInternet.type -> R.drawable.ic_dialog_warning
                    BaseFragment.AlertDialogType.Error.type -> R.drawable.ic_dialog_error
                    else -> R.drawable.ic_dialog_warning
                }
            )
            .showIcon()
            .enableSwipeToDismiss()
            .iconColorFilterRes(R.color.white)
            .iconAnimation(
                FlashAnim.with(this)
                    .animateIcon()
                    .pulse()
                    .alpha()
                    .duration(750)
                    .accelerate()
            )
            .backgroundColorRes(
                when (type) {
                    BaseFragment.AlertDialogType.NoInternet.type -> R.color.koromiko
                    BaseFragment.AlertDialogType.Error.type -> R.color.red
                    else -> R.color.koromiko
                }
            )
            .enterAnimation(
                FlashAnim.with(this)
                    .animateBar()
                    .duration(750)
                    .alpha()
                    .overshoot()
            )
            .exitAnimation(
                FlashAnim.with(this)
                    .animateBar()
                    .duration(400)
                    .accelerateDecelerate()
            )
            .listenBarTaps(object : Flashbar.OnTapListener {
                override fun onTap(flashbar: Flashbar) {
                    flashbar.dismiss()
                }
            }).duration(Flashbar.DURATION_LONG)
            .build().show()

    }



}