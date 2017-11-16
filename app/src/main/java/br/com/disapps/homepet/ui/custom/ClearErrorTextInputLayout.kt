package br.com.disapps.homepet.ui.custom

import android.content.Context
import android.support.design.widget.TextInputLayout
import android.util.AttributeSet

/**
 * Created by felip on 30/08/2017.
 */

class ClearErrorTextInputLayout : TextInputLayout {
    private var clearErrorListener: ClearErrorListener? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun setError(error: CharSequence?) {
        super.setError(error)

        if (clearErrorListener == null)
            clearErrorListener = ClearErrorListener(this)

        this.editText!!.removeTextChangedListener(clearErrorListener)
        this.editText!!.addTextChangedListener(clearErrorListener)
    }
}
