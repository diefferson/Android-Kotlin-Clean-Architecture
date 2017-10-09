package br.com.disapps.homepet.ui.profile

import android.os.Bundle
import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.ui.common.AppFragment
import br.com.disapps.homepet.ui.login.LoginFragment

import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by diefferson.santos on 23/08/17.
 */

class ProfileFragment : AppFragment<IProfileView, ProfilePresenter>(), IProfileView {

    override val fragmentTag: String
        get() = ProfileFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_profile

    override fun createPresenter()= ProfilePresenter(HomePet.instance!!.preferences!!)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dv_profile_avatar.setImageURI("https://lh3.googleusercontent.com/-8lC7WeHFujc/AAAAAAAAAAI/AAAAAAAAAAA/wKXtA6byhOs/s128-c-k/photo.jpg")
        setupLoadingFragment(loading_view)

        logout.setOnClickListener { logout() }
    }

    private fun logout() {
        getPresenter().logout()
    }

    override fun onLogout() {
        appActivityListener!!.replaceFragment(LoginFragment.newInstance())
    }


    companion object {

        fun newInstance() = ProfileFragment()

    }

}
