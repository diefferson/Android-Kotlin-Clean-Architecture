package br.com.disapps.homepet.ui.hotels.adapter

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.support.v4.app.ActivityCompat

import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil

import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.ui.custom.CustomViewHolder

/**
 * Created by diefferson.santos on 23/08/17.
 */

class HotelAdapter(data: List<Hotel>?) : BaseQuickAdapter<Hotel, CustomViewHolder>(R.layout.hotel_item, data) {

    override fun convert(helper: CustomViewHolder, item: Hotel) {
        helper.setText(R.id.hotel_name, item.name)
        if(item.coverImage!= null){
            helper.setImageURI(R.id.hotel_image, item.coverImage)
        }
        helper.setRating(R.id.rating, item.rating)
        helper.setText(R.id.ratings, item.ratingsNumber.toString())
        helper.setText(R.id.comments, item.commentsNumber.toString())
        helper.setText(R.id.price, item.price.toString())
        helper.setText(R.id.distance, item.address)
    }

}
