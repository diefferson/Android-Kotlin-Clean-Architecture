package br.com.disapps.homepet.util.rx

import java.util.ArrayList
import java.util.HashMap

/**
 * Created by diefferson.santos on 23/08/17.
 */

class FormError : HashMap<String, ArrayList<String>>() {

    fun getFirstError(key: String): String {
        return this[key]!!.get(0)
    }

}
