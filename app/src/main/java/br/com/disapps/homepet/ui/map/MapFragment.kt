package br.com.disapps.homepet.ui.map

import android.os.Bundle
import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.model.Coordinate
import br.com.disapps.homepet.ui.common.AppFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*
import android.content.Intent
import android.net.Uri


/**
* Created by diefferson.santos on 23/08/17.
**/

class MapFragment : AppFragment<IMapView, MapPresenter>(), IMapView {

    private var mMap: GoogleMap? = null

    override val fragmentTag: String
        get() = MapFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_map

    override fun createPresenter()= MapPresenter(HomePet.instance!!.hotelRepository!!)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val codeHotel = arguments.getInt("codeHotel")

        mapView.onCreate(savedInstanceState)

        mapView.onResume()

        try {
            MapsInitializer.initialize(activity.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        mapView.getMapAsync { map ->
            mMap = map
            mMap!!.uiSettings.isScrollGesturesEnabled = false
            getPresenter().loadCoordinates(codeHotel)


        }

    }

    override fun fillCoordinates(coordinate: Coordinate) {

        val hotelLocal = LatLng(coordinate.latitude, coordinate.longitude)
        mMap!!.addMarker(MarkerOptions().position(hotelLocal).title("").snippet(""))

        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(hotelLocal, 15f))

        mMap!!.setOnMapClickListener {
            val gmmIntentUri = Uri.parse("geo:"+coordinate.latitude+","+coordinate.longitude)
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.`package` = "com.google.android.apps.maps"
            startActivity(mapIntent)
        }

    }

    companion object {

        fun newInstance(codeHotel: Int): MapFragment {

            val mapFragment = MapFragment()

            val args = Bundle()
            args.putInt("codeHotel", codeHotel)
            mapFragment.arguments = args

            return mapFragment
        }
    }
}
