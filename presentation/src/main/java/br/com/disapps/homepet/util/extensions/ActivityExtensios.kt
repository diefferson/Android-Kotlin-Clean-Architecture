package br.com.disapps.homepet.util.extensions

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast

/**
 * Created by diefferson on 28/11/2017.
 */
fun Activity.toast(message : String){
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun <T> Activity.startActivity(clazz: Class<T>){
    startActivity(Intent(this, clazz))
}

fun <T> Activity.startActivity(clazz: Class<T>, extras : Bundle){
    val intent = Intent(this, clazz)
    intent.putExtras(extras)
    startActivity(intent)
}