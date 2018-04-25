package br.com.disapps.homepet.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout

/**
 * Created by diefferson on 29/11/2017.
 */
abstract class BaseFragmentActivity: AppCompatActivity(), IBaseFragmentActivityListener{

    abstract val viewModel: BaseViewModel
    abstract val activityLayout: Int
    abstract val container: FrameLayout

    private val fragmentTransaction : FragmentTransaction
        get() = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activityLayout)
    }

    override fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun setDisplayHomeAsUpEnabled() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun replaceFragment(fragment: Fragment) {
        if(supportFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) == null){
            val ft = fragmentTransaction
            ft.replace(container.id, fragment, fragment.javaClass.simpleName)
            ft.commit()
        }
    }

    override fun replaceAndBackStackFragment(fragment: Fragment) {
        if(supportFragmentManager.findFragmentByTag(fragment.javaClass.simpleName) == null){
            val ft = fragmentTransaction
            ft.replace(container.id, fragment, fragment.javaClass.simpleName)
            ft.addToBackStack(fragment.javaClass.simpleName)
            ft.commit()
        }
    }
}