package br.com.disapps.homepet.ui.custom

import android.support.design.widget.TextInputLayout
import android.text.Editable
import android.text.TextWatcher

/**
 * Created by felip on 30/08/2017.
 */

class ClearErrorListener(private val parent: TextInputLayout) : TextWatcher {

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        parent.error = null
        parent.isErrorEnabled = false
    }

    override fun afterTextChanged(editable: Editable) {

    }
}
