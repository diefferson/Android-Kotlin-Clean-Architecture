package br.com.disapps.homepet.ui.custom

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.disapps.homepet.R

/**
 * Created by diefferson.santos on 04/09/17.
 */

class LoadingView : DialogFragment() {

    companion object {
        fun newInstance(): LoadingView {
            return LoadingView()
        }
    }

    val viewTag: String
        get() = LoadingView::class.java.simpleName

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun cancelableOnBackPressed(cancelable: Boolean) {
        super.setCancelable(cancelable)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.loading_view, container, false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)

        return view
    }

}