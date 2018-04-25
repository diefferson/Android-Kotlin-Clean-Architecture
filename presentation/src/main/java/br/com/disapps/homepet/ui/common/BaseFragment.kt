package br.com.disapps.homepet.ui.common

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by diefferson on 29/11/2017.
 */
abstract class BaseFragment : Fragment() {

    abstract val viewModel: BaseViewModel
    abstract val fragmentLayout: Int

    var hasTabs: Boolean = false

    val iAppActivityListener: IBaseFragmentActivityListener by lazy{
        activity as IBaseFragmentActivityListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        return inflater.inflate(fragmentLayout, container,false)
    }

}