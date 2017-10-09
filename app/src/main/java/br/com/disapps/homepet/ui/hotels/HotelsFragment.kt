package br.com.disapps.homepet.ui.hotels

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.data.ws.request.HotelsRequest
import br.com.disapps.homepet.ui.common.AppFragment
import br.com.disapps.homepet.ui.hotel.HotelActivity
import br.com.disapps.homepet.ui.hotels.adapter.HotelAdapter
import br.com.disapps.homepet.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_hotels.*
import kotlinx.android.synthetic.main.include_filter_layout.*
import android.support.v7.widget.RecyclerView


/**
 * Created by diefferson.santos on 23/08/17.
 */

class HotelsFragment : AppFragment<IHotelsView, HotelsPresenter>(), IHotelsView {

    private var hotelAdapter: HotelAdapter? = null

    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null

    override val fragmentTag: String
        get() = HotelsFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_hotels

    override fun createPresenter() = HotelsPresenter(HomePet.instance!!.hotelRepository!!)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        appActivityListener!!.setTitle("Pesquisar")

        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet!!)
        mBottomSheetBehavior!!.peekHeight = 0

        setupLoadingFragment(loading_view)

        hotel_recycler.setHasFixedSize(true)
        hotel_recycler.layoutManager = LinearLayoutManager(context)

        getPresenter().loadHoteis(HotelsRequest())

        apply_sort.setOnClickListener { applySort() }

        fab_filter.setOnClickListener { fabFilter() }

        hotel_recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0)
                    fab_filter.hide()
                else if (dy < 0)
                    fab_filter.show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.hotels_menu, menu)
    }

     fun fabFilter(){

        if (mBottomSheetBehavior!!.state == BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_EXPANDED
            fab_filter.setImageDrawable(resources.getDrawable(R.drawable.ic_arrow_bottom))
        } else {
            mBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
            fab_filter.setImageDrawable(resources.getDrawable(R.drawable.ic_filter))
        }

    }

    override fun fillHotelAdapter(hoteis: List<Hotel>) {

        hotelAdapter = HotelAdapter(hoteis)

        hotelAdapter!!.setOnItemClickListener { adapter: BaseQuickAdapter<*, *>, view: View, position: Int ->
            val intent = Intent(activity, HotelActivity::class.java)
            val args = Bundle()
            args.putInt("hotelCode", hotelAdapter!!.getItem(position)!!.code)
            intent.putExtras(args)
            startActivity(intent)

        }

        hotel_recycler.adapter = hotelAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.profile-> {
                appActivityListener!!.replaceAndBackStackFragment(ProfileFragment.newInstance())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun applySort() {
        val request = HotelsRequest()
        mBottomSheetBehavior!!.state = BottomSheetBehavior.STATE_COLLAPSED
        fab_filter.setImageDrawable(resources.getDrawable(R.drawable.ic_filter))
        when (sort.checkedRadioButtonId) {
            R.id.price -> request.sort = "price"
            R.id.comments -> request.sort = "comments"
            R.id.rating -> request.sort = "rating"
        }

        when (sense.checkedRadioButtonId) {
            R.id.asc -> request.sense = "asc"
            R.id.desc -> request.sense = "desc"
        }

        getPresenter().loadHoteis(request)
    }

    companion object {
        fun newInstance()= HotelsFragment()
    }
}
