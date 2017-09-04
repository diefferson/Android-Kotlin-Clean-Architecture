package br.com.disapps.homepet.ui.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.ui.common.AppFragment;
import butterknife.BindView;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class MapFragment  extends AppFragment<IMapView , MapPresenter> implements IMapView {

    private GoogleMap mMap;

    @BindView(R.id.mapView)
    MapView mMapView;

    public  static  MapFragment newInstance(){
        return new MapFragment();
    }

    @Override
    public String getFragmentTag() {
        return MapFragment.class.getSimpleName();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_map;
    }

    @Override
    public MapPresenter createPresenter() {
        return new MapPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {

                mMap = map;
                mMap.getUiSettings().setScrollGesturesEnabled(false);

                LatLng sydney = new LatLng(-25.550263, -49.263760);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Minha casa").snippet("Ola sou eu"));

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 15));

            }
        });

    }
}
