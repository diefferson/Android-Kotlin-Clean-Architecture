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

    private val locationManager: LocationManager? = null
    private val provider: String? = null

    override fun convert(helper: CustomViewHolder, item: Hotel) {
        helper.setText(R.id.hotel_name, item.name)
        if(item.coverImage!= null){
            helper.setImageURI(R.id.hotel_image, item.coverImage!!)
        }
        helper.setRating(R.id.rating, item.rating)
        helper.setText(R.id.ratings, item.ratingsNumber.toString())
        helper.setText(R.id.comments, item.commentsNumber.toString())
        helper.setText(R.id.price, item.price.toString())
        helper.setText(R.id.distance, distance(item))
    }

    //TODO: REFECTOR
    private fun distance(hotel: Hotel): String {

        //        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        //
        //        Criteria criteria = new Criteria();
        //        provider = locationManager.getBestProvider(criteria, false);
        //
        //
        //        Location location = locationManager.getLastKnownLocation(provider);
        //
        //        LatLng initialPosition = new LatLng(location.getLatitude(), location.getLongitude());
        //        LatLng finalPosition = new LatLng(hotel.getCoordenates().getLatitude(),hotel.getCoordenates().getLongitude());
        //
        //        double distance = SphericalUtil.computeDistanceBetween(initialPosition, finalPosition);

        //        return formatNumber(distance);

        return ""

    }

    private fun formatNumber(distance: Double): String {
        var distance = distance

        var unit = " m"
        if (distance > 1000) {
            distance /= 1000.0
            unit = " km"
        }

        return String.format("%4.3f%s distante", distance, unit)
    }


}
