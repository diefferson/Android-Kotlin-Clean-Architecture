package br.com.disapps.homepet.ui.custom

import android.content.res.ColorStateList
import android.support.annotation.IdRes
import android.support.v4.view.ViewCompat
import android.view.View
import android.widget.ImageView
import br.com.disapps.homepet.util.extensions.setCircleImageURI
import br.com.disapps.homepet.util.extensions.setImageURICrop

import com.chad.library.adapter.base.BaseViewHolder

/**
 * Created by diefferson.santos on 23/08/17.
 **/
class CustomViewHolder(view: View) : BaseViewHolder(view) {

    fun setImageURI(@IdRes viewId: Int, value: String): BaseViewHolder {
        val view = getView<ImageView>(viewId)
        view.setImageURICrop(value)
        return this
    }

    fun setCircleImageURI(@IdRes viewId: Int, value: String): BaseViewHolder {
        val view = getView<ImageView>(viewId)
        view.setCircleImageURI(value)
        return this
    }

    fun setBackgroundTintList(@IdRes viewId: Int, color: ColorStateList): BaseViewHolder {
        val view = getView<View>(viewId)
        ViewCompat.setBackgroundTintList(view, color)
        return this
    }

}
