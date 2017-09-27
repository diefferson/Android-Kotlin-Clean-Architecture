package br.com.disapps.homepet.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.content.LocalBroadcastManager

import br.com.disapps.homepet.util.ConnectionUtils

/**
 * Created by diefferson.santos on 23/08/17.
 */
class NetworkChangeReceiver : BroadcastReceiver() {
    var isOnline: Boolean = false
        private set

    override fun onReceive(context: Context, intent: Intent) {
        val localIntent = Intent(NOTIFY_NETWORK_CHANGE)
        isOnline = ConnectionUtils.isOnline(context)
        localIntent.putExtra(EXTRA_IS_CONNECTED, isOnline)
        LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent)
    }

    companion object {

        val NOTIFY_NETWORK_CHANGE = "android.net.conn.CONNECTIVITY_CHANGE"
        val EXTRA_IS_CONNECTED = "EXTRA_IS_CONNECTED"
    }

}
