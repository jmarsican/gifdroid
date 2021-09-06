package com.javiermarsicano.gifdroid.base.mvp

interface MVPView {
    fun showLoading()
    fun hideLoading()
    fun onError(resId: Int)
    fun onError(message: String?)
    fun hideKeyboard()
}