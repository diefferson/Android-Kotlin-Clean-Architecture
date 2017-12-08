package br.com.disapps.homepet.ui.main

import android.os.Bundle
import br.com.disapps.homepet.R
import br.com.disapps.homepet.ui.common.AppActivity
import br.com.disapps.homepet.ui.hotels.HotelsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContainer(container)

        replaceFragment(HotelsFragment.newInstance())
    }
}
