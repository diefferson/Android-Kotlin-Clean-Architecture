package br.com.disapps.homepet.util.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * Created by diefferson on 28/11/2017.
 */
fun ImageView.setImageURICrop(url: String){
    Glide.with(context)
            .load(url)
            .apply(RequestOptions.centerCropTransform())
            .into(this)
}

fun ImageView.setImageURIFit(url: String){
    Glide.with(context)
            .load(url)
            .apply(RequestOptions.fitCenterTransform())
            .into(this)
}

fun ImageView.setCircleImageURI( url: String){
    Glide.with(context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(this)
}