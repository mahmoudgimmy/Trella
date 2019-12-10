package com.elm.entities.interfaces

// interface which responsible of communication between fragment and BaseActivity
interface IActivityChanges {
    fun showLoading(show: Boolean)
    fun showAlert(title: String,message:String,type: Int)
}