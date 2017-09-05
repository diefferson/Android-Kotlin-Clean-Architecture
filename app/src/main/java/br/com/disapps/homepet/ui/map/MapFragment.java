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
import br.com.disapps.homepet.app.HomePet;
import br.com.disapps.homepet.data.model.Coordinate;
import br.com.disapps.homepet.ui.comments.CommentsFragment;
import br.com.disapps.homepet.ui.common.AppFragment;
import butterknife.BindView;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class MapFragment  extends AppFragment<IMapView , MapPresenter> implements IMapView {

    private GoogleMap mMap;

    @BindView(R.id.mapView)
    MapView mMapView;

    public  static  MapFragment newInstance(int codeHotel){

        MapFragment mapFragment = new MapFragment();

        Bundle args = new Bundle();
        args.putInt("codeHotel", codeHotel);
        mapFragment.setArguments(args);

        return mapFragment;
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
        return new MapPresenter(HomePet.Companion.getInstance().getHoteltRepository());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int codeHotel = getArguments().getInt("codeHotel");

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
                getPresenter().loadCoordinates(codeHotel);

            }
        });

    }

    @Override
    public void fillCoordinates(Coordinate coordinate) {

        LatLng hotelLocal = new LatLng(coordinate.getLatitude(), coordinate.getLongitude());
        mMap.addMarker(new MarkerOptions().position(hotelLocal).title("").snippet(""));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hotelLocal, 15));

    }
}
