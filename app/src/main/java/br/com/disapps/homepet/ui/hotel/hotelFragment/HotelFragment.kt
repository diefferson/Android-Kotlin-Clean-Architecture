package br.com.disapps.homepet.ui.hotel.hotelFragment

import android.os.Bundle
import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.ui.common.AppFragment
import br.com.disapps.homepet.ui.hotel.adapter.HotelViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_hotel.*

/**
 * Created by diefferson.santos on 03/09/17.
 **/
class HotelFragment : AppFragment<IHotelView, HotelPresenter>(), IHotelView {

    private val hotel: Hotel? = null

    override val fragmentTag: String
        get() = HotelFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_hotel

    override fun createPresenter() = HotelPresenter()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val codeHotel = arguments.getInt("codeHotel")

        view_pager.adapter = HotelViewPagerAdapter(childFragmentManager, context, codeHotel)
        tabs!!.setupWithViewPager(view_pager)
    }

    companion object {

        fun newInstance(codeHotel: Int): HotelFragment {

            val hotelFragment = HotelFragment()

            val args = Bundle()
            args.putInt("codeHotel", codeHotel)
            hotelFragment.arguments = args

            return hotelFragment
        }
    }

}
