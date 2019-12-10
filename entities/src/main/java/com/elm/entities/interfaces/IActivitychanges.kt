package com.elm.entities.interfaces


interface IActivityChanges {
    fun showLoading(show: Boolean)
    fun showAlert(title: String,message:String,type: Int)
}