package br.com.disapps.homepet.ui.main

import android.os.Bundle
import android.widget.FrameLayout
import br.com.disapps.homepet.R
import br.com.disapps.homepet.ui.common.BaseFragmentActivity
import br.com.disapps.homepet.ui.common.BaseViewModel
import br.com.disapps.homepet.ui.hotels.HotelsFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.getViewModel

class MainActivity : BaseFragmentActivity() {

    override val viewModel: BaseViewModel
        get() = getViewModel()

    override val activityLayout = R.layout.activity_main

    override val container : FrameLayout by lazy { vContainer  }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        replaceFragment(HotelsFragment.newInstance())
    }
}
