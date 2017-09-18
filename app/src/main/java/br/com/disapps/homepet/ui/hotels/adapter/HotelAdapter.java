package br.com.disapps.homepet.ui.hotels.adapter;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

import java.util.List;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.app.HomePet;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.ui.custom.CustomViewHolder;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class HotelAdapter extends BaseQuickAdapter<Hotel, CustomViewHolder> {

    private LocationManager locationManager;
    private String provider;


    public HotelAdapter(@Nullable List<Hotel> data) {
        super(R.layout.hotel_item, data);
    }

    @Override
    protected void convert(CustomViewHolder helper, Hotel item) {
        helper.setText(R.id.hotel_name, item.getName());
        helper.setImageURI(R.id.hotel_image, item.getCoverImage());
        helper.setRating(R.id.rating, item.getRating());
        helper.setText(R.id.ratings, String.valueOf(item.getRatingsNumber()));
        helper.setText(R.id.comments, String.valueOf(item.getCommentsNumber()));
        helper.setText(R.id.price, String.valueOf(item.getPrice()));
        helper.setText(R.id.distance, distance(item));
    }

    private String distance(Hotel hotel) {

        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(provider);

        LatLng initialPosition = new LatLng(location.getLatitude(), location.getLongitude());
        LatLng finalPosition = new LatLng(hotel.getCoordenates().getLatitude(),hotel.getCoordenates().getLongitude());

        double distance = SphericalUtil.computeDistanceBetween(initialPosition, finalPosition);

        return formatNumber(distance);

    }

    private String formatNumber(double distance) {

        String unit = " m";
        if (distance > 1000) {
            distance /= 1000;
            unit = " km";
        }

        return String.format("%4.3f%s distante", distance, unit);
    }


}
