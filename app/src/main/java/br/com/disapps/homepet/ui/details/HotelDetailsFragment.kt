package br.com.disapps.homepet.ui.details

import android.os.Bundle
import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.ui.common.AppFragment
import br.com.disapps.homepet.ui.details.adapter.ServiceAdapter
import kotlinx.android.synthetic.main.fragment_hotel_details.*

/**
 * Created by diefferson.santos on 31/08/17.
* */
class HotelDetailsFragment : AppFragment<IHotelDetailsView, HotelDetailsPresenter>(), IHotelDetailsView {

    private var serviceAdapter: ServiceAdapter? = null

    override val fragmentTag: String
        get() = HotelDetailsFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_hotel_details

    override fun createPresenter(): HotelDetailsPresenter {
        return HotelDetailsPresenter(HomePet.instance!!.hotelRepository!!)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLoadingFragment(loading_view)

        val codeHotel = arguments.getInt("codeHotel")

        getPresenter().loadHotel(codeHotel)

        validateLogin()

    }

    private fun validateLogin() {
        if (!HomePet.instance!!.preferences!!.isLogged) {
            rating_bt.visibility = View.INVISIBLE
            comment_bt.visibility = View.INVISIBLE
        }
    }

    override fun fillHotelDetails(hotel: Hotel) {
        hotel_description.text = hotel.description
        hotel_address.text = hotel.getCompleteAddress()
        ratings.text = hotel.rating.toString()
        comments.text = hotel.commentsNumber.toString()
        rating.rating = hotel.rating
        content.visibility = View.VISIBLE
        serviceAdapter = ServiceAdapter(hotel.services)
        service_recycler.adapter = serviceAdapter
    }

    companion object {

        fun newInstance(codeHotel: Int): HotelDetailsFragment {

            val hotelDetailsFragment = HotelDetailsFragment()

            val args = Bundle()
            args.putInt("codeHotel", codeHotel)
            hotelDetailsFragment.arguments = args

            return hotelDetailsFragment

        }
    }
}
