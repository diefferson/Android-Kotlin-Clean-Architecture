package br.com.disapps.homepet.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import android.widget.FrameLayout
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.ui.common.AppActivity
import br.com.disapps.homepet.ui.hotels.HotelsFragment
import br.com.disapps.homepet.ui.login.LoginFragment
import br.com.disapps.homepet.ui.profile.ProfileFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContainer(container)

        val menu = navigation.menu
        setInitialFragment(menu.getItem(0))

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(HotelsFragment.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        //                case R.id.navigation_map:
        //                        replaceFragment(MapFragment.newInstance(1));
        //                    return true;
            R.id.navigation_profile -> {
                if (HomePet.instance!!.preferences!!.isLogged) {
                    replaceFragment(ProfileFragment.newInstance())
                } else {
                    replaceFragment(LoginFragment.newInstance())
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    private fun setInitialFragment(menuItem: MenuItem) {
        mOnNavigationItemSelectedListener.onNavigationItemSelected(menuItem)
    }

    override fun showLoading(cancelable: Boolean) {

    }

    override fun dismissLoading() {

    }
}
