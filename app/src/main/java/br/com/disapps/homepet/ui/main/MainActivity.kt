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
import org.jetbrains.anko.toast

class MainActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContainer(container)

        replaceFragment(HotelsFragment.newInstance())
    }

}
