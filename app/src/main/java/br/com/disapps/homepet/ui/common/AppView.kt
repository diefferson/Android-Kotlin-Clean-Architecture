package br.com.disapps.homepet.ui.common

import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by diefferson.santos on 23/08/17.
 */
interface AppView : MvpView, IErrorHandlerView {
    fun hasInternetConnection(): Boolean
    fun showLoading(cancelable: Boolean)
    fun dismissLoading()
}
