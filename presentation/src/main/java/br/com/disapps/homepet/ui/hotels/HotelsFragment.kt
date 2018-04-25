package br.com.disapps.homepet.ui.hotels

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.ui.common.BaseFragment
import br.com.disapps.homepet.ui.hotels.adapter.HotelAdapter
import kotlinx.android.synthetic.main.fragment_hotels.*
import kotlinx.android.synthetic.main.include_filter_layout.*
import org.koin.android.architecture.ext.viewModel


/**
 * Created by diefferson.santos on 23/08/17.
 */

class HotelsFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener{

    override val viewModel by viewModel<HotelsViewModel>()

    override val fragmentLayout = R.layout.fragment_hotels

    private lateinit var mBottomSheetBehavior: BottomSheetBehavior<*>

    private val hotelAdapter: HotelAdapter by lazy {
        HotelAdapter(ArrayList())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        iAppActivityListener.setTitle(getString(R.string.search))
        observeViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getHotels()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.hotels_menu, menu)
        if(menu!!.findItem(R.id.action_menu_edit)!= null){
            menu.removeItem(R.id.action_menu_edit)
        }
    }

    private fun fabFilter(){

        if (mBottomSheetBehavior.state == BottomSheetBehavior.STATE_COLLAPSED) {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            fab_filter.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_arrow_bottom))
        } else {
            mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            fab_filter.setImageDrawable(ContextCompat.getDrawable(context!!,R.drawable.ic_filter))
        }

    }

    private fun observeViewModel() {
        viewModel.hotels.observe(this, Observer {
            hotelAdapter.setNewData(it)
        })
    }

    private fun initViews() {

        mBottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet).apply {
            peekHeight = 0
        }

        hotel_recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = hotelAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                    if (dy > 0)
                        fab_filter.hide()
                    else if (dy < 0)
                        fab_filter.show()
                }
            })
        }

        swipe_refresh_container.setOnRefreshListener(this)

        apply_sort.setOnClickListener { applySort() }
        fab_filter.setOnClickListener { fabFilter() }
    }


    private fun applySort() {
        mBottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        fab_filter.setImageDrawable(ContextCompat.getDrawable(context!!, R.drawable.ic_filter))
        when (sort.checkedRadioButtonId) {
            R.id.price -> viewModel.sort = "price"
            R.id.comments -> viewModel.sort = "comments"
            R.id.rating -> viewModel.sort = "rating"
        }

        when (sense.checkedRadioButtonId) {
            R.id.asc -> viewModel.sense = "asc"
            R.id.desc -> viewModel.sense = "desc"
        }

        viewModel.getHotels()
    }

    override fun onRefresh() {
        viewModel.getHotels()
    }

    companion object {
        fun newInstance()= HotelsFragment()
    }
}
