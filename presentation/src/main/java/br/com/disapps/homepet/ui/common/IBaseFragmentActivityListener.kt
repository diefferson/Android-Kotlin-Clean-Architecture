package br.com.disapps.homepet.ui.common

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import br.com.disapps.homepet.ui.common.BaseFragment

/**
 * Created by diefferson on 29/11/2017.
 */
interface IBaseFragmentActivityListener {

    fun setTitle(title: String)

    fun setDisplayHomeAsUpEnabled()

    fun replaceFragment(fragment: Fragment)

    fun replaceAndBackStackFragment(fragment: Fragment)
}
