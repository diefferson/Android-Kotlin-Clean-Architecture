package br.com.disapps.homepet.ui.common

import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.FrameLayout

import br.com.disapps.homepet.ui.custom.LoadingView

/**
 * Created by diefferson.santos on 23/08/17.
 */
interface IAppActivityListener {

    fun setContainer(container: FrameLayout?)
    fun setTitle(title: String)
    fun setToolbar(toolbar: Toolbar)
    fun replaceFragment(fragment: AppFragment<*, *>)
    fun replaceAndBackStackFragment(fragment: AppFragment<*, *>)
    fun hasInternetConnection(): Boolean
    fun setupLoadingActivity(loadingView: LoadingView)
    fun showLoading(cancelable: Boolean)
    fun dismissLoading()
    fun displayArrowBack()
    fun hideArrowBack()

}