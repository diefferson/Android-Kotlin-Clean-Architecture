package br.com.disapps.homepet.ui.hotel.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter


import br.com.disapps.homepet.ui.comments.CommentsFragment
import br.com.disapps.homepet.ui.details.HotelDetailsFragment
import br.com.disapps.homepet.ui.map.MapFragment

/**
 * Created by diefferson.santos on 31/08/17.
 **/

class HotelViewPagerAdapter(fm: FragmentManager, private val mContext: Context, private val codeHotel: Int) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return HotelDetailsFragment.newInstance(codeHotel)
            1 -> return CommentsFragment.newInstance(codeHotel)
            2 -> return MapFragment.newInstance(codeHotel)
        }

        // return LectureDetailsFragment.newInstance();
        return null
    }

    override fun getCount(): Int {
        return FRAGMENT_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {

        when (position) {
            0 -> return "Detalhes"
            1 -> return "Comentarios"
            2 -> return "Mapa"
        }

        return null
    }

    companion object {

        private val FRAGMENT_COUNT = 3
    }
}
