package br.com.disapps.homepet.ui.hotels

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.widget.SwipeRefreshLayout
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
import br.com.disapps.homepet.ui.login.LoginFragment


/**
 * Created by diefferson.santos on 23/08/17.
 */

class HotelsFragment : AppFragment<IHotelsView, HotelsPresenter>(), IHotelsView, SwipeRefreshLayout.OnRefreshListener{

    private var hotelAdapter: HotelAdapter? = null

    private var mBottomSheetBehavior: BottomSheetBehavior<*>? = null

    var request = HotelsRequest()

    override val fragmentTag: String
        get() = HotelsFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_hotels

    override fun createPresenter() = HotelsPresenter(HomePet.instance!!.hotelRepository)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        appActivityListener!!.setTitle("Pesquisar")

        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet!!)
        mBottomSheetBehavior!!.peekHeight = 0

        setupLoadingFragment(loading_view)

        hotel_recycler.setHasFixedSize(true)
        hotel_recycler.layoutManager = LinearLayoutManager(context)

        swipe_refresh_container.setOnRefreshListener(this)

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

    override fun onResume() {
        super.onResume()
        getPresenter().loadHoteis(request)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.hotels_menu, menu)
        if(menu!!.findItem(R.id.action_menu_edit)!= null){
            menu.removeItem(R.id.action_menu_edit)
        }
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
                if(HomePet.instance!!.preferences.isLogged){
                    appActivityListener!!.replaceAndBackStackFragment(ProfileFragment.newInstance())
                }else{
                    appActivityListener!!.replaceAndBackStackFragment(LoginFragment.newInstance())
                }

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun applySort() {
        request = HotelsRequest()
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

    override fun onRefresh() {
        getPresenter().loadHoteis(request)
    }

    override fun showLoading(cancelable: Boolean) {
        swipe_refresh_container.isRefreshing = true
    }

    override fun dismissLoading() {
        swipe_refresh_container.isRefreshing = false
    }
}
