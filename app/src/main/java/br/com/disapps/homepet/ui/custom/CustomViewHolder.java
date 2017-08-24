package br.com.disapps.homepet.ui.custom;

import android.content.res.ColorStateList;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class CustomViewHolder extends BaseViewHolder {

    public CustomViewHolder(View view) {
        super(view);
    }

    public BaseViewHolder setImageURI(@IdRes int viewId, String value) {
        SimpleDraweeView view = getView(viewId);
        view.setImageURI(value);
        return this;
    }

    public BaseViewHolder setBackgroundTintList(@IdRes int viewId, ColorStateList color){
        View view = getView(viewId);
        ViewCompat.setBackgroundTintList(view,color);
        return this;
    }

}
