package br.com.disapps.homepet.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

/**
 * Created by diefferson.santos on 23/08/17.
 */
object ConnectionUtils {

    internal var TYPE_WIFI = 1
    internal var TYPE_MOBILE = 2
    internal var TYPE_NOT_CONNECTED = 0

    internal fun getConnectivityStatus(context: Context): Int {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = cm.activeNetworkInfo
        if (null != activeNetwork) {
            if (activeNetwork.type == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI

            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE
        }
        return TYPE_NOT_CONNECTED
    }


    fun isOnline(context: Context): Boolean {
        val status = getConnectivityStatus(context)
        return status == TYPE_WIFI || status == TYPE_MOBILE
    }
}
