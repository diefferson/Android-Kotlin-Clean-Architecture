package br.com.disapps.homepet.ui.common

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.support.v4.content.LocalBroadcastManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import br.com.disapps.homepet.receiver.NetworkChangeReceiver
import br.com.disapps.homepet.ui.custom.LoadingView
import br.com.disapps.homepet.util.rx.RxHttpError

/**
 * Created by diefferson.santos on 23/08/17.
 */

abstract class AppActivity : AppCompatActivity(), IAppActivityListener, IErrorHandlerView, AppView {

    private var container: FrameLayout? = null
    private var loadingView: LoadingView? = null

    private var networkReceiver: NetworkChangeReceiver? = null
    private val internetListenerList: List<InternetConnectionListener>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBroadcastReceiver(true)
    }

    override fun setupLoadingActivity(loadingView: LoadingView) {
        this.loadingView = loadingView
    }

    override fun showLoading(cancelable: Boolean) {
        if (loadingView != null) {
            loadingView!!.cancelableOnBackPressed(cancelable)
            loadingView!!.show(supportFragmentManager, loadingView!!.viewTag)
        }
    }

    override fun dismissLoading() {
        if (loadingView != null) {
            loadingView!!.dismiss()
        }
    }


    override fun setContainer(container: FrameLayout?) {
        this.container = container
    }

    override fun replaceFragment(fragment: AppFragment<*, *>) {
        val ft = fragmentTransaction
        ft.replace(container!!.id, fragment, fragment.fragmentTag)
        ft.commit()
        hideArrowBack()
    }

    override fun replaceAndBackStackFragment(fragment: AppFragment<*, *>) {
        val ft = fragmentTransaction
        ft.replace(container!!.id, fragment, fragment.fragmentTag)
        ft.addToBackStack(fragment.fragmentTag)
        ft.commit()
        hideArrowBack()
    }

    private val fragmentTransaction: FragmentTransaction
        get() = supportFragmentManager.beginTransaction()


    override fun displayArrowBack() {
        if(supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
    }

    override fun hideArrowBack() {
        if(supportActionBar != null){
            supportActionBar!!.setDisplayHomeAsUpEnabled(false)
            supportActionBar!!.setDisplayShowHomeEnabled(false)
        }

    }

    override fun setTitle(title: String) {
        supportActionBar!!.title = title
    }

    override fun setToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun hasInternetConnection(): Boolean {
        return networkReceiver!!.isOnline
    }

    override fun onResume() {
        super.onResume()
        setupBroadcastReceiver(true)
    }

    override fun onPause() {
        setupBroadcastReceiver(false)
        super.onPause()
    }

    override fun error(error: RxHttpError) {}

    override fun error(e: Throwable) {}

    fun setupBroadcastReceiver(on: Boolean) {
        if (networkReceiver == null)
            networkReceiver = NetworkChangeReceiver()

        if (on) {
            registerReceiver(networkReceiver, IntentFilter(NetworkChangeReceiver.NOTIFY_NETWORK_CHANGE))

            val customFilter = IntentFilter(NetworkChangeReceiver.NOTIFY_NETWORK_CHANGE)
            LocalBroadcastManager.getInstance(this).registerReceiver(mLocalReceiver, customFilter)
        } else {
            unregisterReceiver(networkReceiver)
        }
    }

    private val mLocalReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val internetConnection = intent.getBooleanExtra(NetworkChangeReceiver.EXTRA_IS_CONNECTED, false)

            if (internetListenerList != null && !internetListenerList.isEmpty()) {
                for (listener in internetListenerList) {
                    listener.onInternetConnectionChange(internetConnection)
                }
            }
        }
    }

    override fun inflateView(resource: Int, viewGroup: View): View =
            layoutInflater.inflate(resource, viewGroup.parent as ViewGroup, false)

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        hideArrowBack()
        super.onBackPressed()
    }
}