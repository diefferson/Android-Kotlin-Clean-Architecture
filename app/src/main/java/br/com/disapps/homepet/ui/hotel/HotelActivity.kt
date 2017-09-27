package br.com.disapps.homepet.ui.hotel

import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.view.MenuItem
import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.ui.common.AppActivity
import br.com.disapps.homepet.ui.hotel.hotelFragment.HotelFragment
import br.com.disapps.homepet.ui.hotels.adapter.ImageViewPagerAdapter
import kotlinx.android.synthetic.main.activity_hotel.*

/**
 * Created by diefferson.santos on 31/08/17.
 */

class HotelActivity : AppActivity(), AppBarLayout.OnOffsetChangedListener, IHotelView {

    private var hotelPresenter: HotelPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel)
        bindView()

        setToolbar(toolbar)
        setContainer(container!!)

        val hotelCode = intent.extras!!.getInt("hotelCode")

        presenter.loadHotel(hotelCode)

        replaceFragment(HotelFragment.newInstance(hotelCode))

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when (id) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showLoading(cancelable: Boolean) {
        loading_view.visibility = View.VISIBLE
    }

    override fun dismissLoading() {
        loading_view.visibility = View.GONE
    }

    override fun fillHeaderHotel(hotel: Hotel) {

        setTitle(hotel.name!!)

        image_slide!!.adapter = ImageViewPagerAdapter(this, hotel.images!!)
        pageIndicatorView!!.setViewPager(image_slide)
        pageIndicatorView!!.visibility = View.VISIBLE

    }

    private val presenter: HotelPresenter
        get() {

            if (hotelPresenter == null) {
                createPresenter()
            }

            return hotelPresenter!!
        }

    private fun createPresenter() {
        hotelPresenter = HotelPresenter(HomePet.instance!!.hotelRepository!!)
        hotelPresenter!!.attachView(this)
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {

    }

}