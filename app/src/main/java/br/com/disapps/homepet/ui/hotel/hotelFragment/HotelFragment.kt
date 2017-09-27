package br.com.disapps.homepet.ui.hotel.hotelFragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View

import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.ui.common.AppFragment
import br.com.disapps.homepet.ui.hotel.adapter.HotelViewPagerAdapter
import butterknife.BindView
import kotlinx.android.synthetic.main.fragment_hotel.*

/**
 * Created by diefferson.santos on 03/09/17.
 **/

class HotelFragment : AppFragment<IHotelView, HotelPresenter>(), IHotelView {

    companion object {

        fun newInstance(codeHotel: Int): HotelFragment {

            val hotelFragment = HotelFragment()

            val args = Bundle()
            args.putInt("codeHotel", codeHotel)
            hotelFragment.arguments = args

            return hotelFragment
        }
    }

    private val hotel: Hotel? = null

    override val fragmentTag: String
        get() = HotelFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_hotel

    override fun createPresenter(): HotelPresenter {
        return HotelPresenter()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val codeHotel = arguments.getInt("codeHotel")

        view_pager.adapter = HotelViewPagerAdapter(childFragmentManager, context, codeHotel)
        tabs!!.setupWithViewPager(view_pager)
    }

}
