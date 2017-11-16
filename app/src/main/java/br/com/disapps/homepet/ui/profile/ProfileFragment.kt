package br.com.disapps.homepet.ui.profile

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import br.com.disapps.homepet.R
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.model.User
import br.com.disapps.homepet.ui.common.AppFragment
import br.com.disapps.homepet.ui.login.LoginFragment
import br.com.disapps.homepet.ui.profile.edit.EditProfileFragment

import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * Created by diefferson.santos on 23/08/17.
 */

class ProfileFragment : AppFragment<IProfileView, ProfilePresenter>(), IProfileView {

    private val mUser: User by lazy {
        HomePet.instance!!.preferences!!.getUser()
    }

    override val fragmentTag: String
        get() = ProfileFragment::class.java.simpleName

    override val fragmentLayout: Int
        get() = R.layout.fragment_profile

    override fun createPresenter()= ProfilePresenter(HomePet.instance!!.preferences!!)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        appActivityListener!!.displayArrowBack()

        appActivityListener!!.setTitle("Perfil")

        logout.setOnClickListener { logout() }
    }

    override fun onResume() {
        super.onResume()

        fillUser(HomePet.instance!!.preferences!!.getUser())
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.action_menu_edit-> {
                appActivityListener!!.replaceAndBackStackFragment(EditProfileFragment.newInstance())
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun logout() {
        getPresenter().logout()
    }

    override fun onLogout() {
        appActivityListener!!.replaceFragment(LoginFragment.newInstance())
    }

    private fun fillUser(user : User){
        if(user.avatar!= null){
            dv_profile_avatar.setImageURI(user.avatar)
        }
        profile_name_tv.text = user.name
        profile_email_tv.text = user.email
        profile_phone_tv.text = user.phone
        profile_address_tv.text = user.address
        profile_city_tv.text = user.city
        profile_uf_tv.text = user.uf
    }

    companion object {
        fun newInstance() = ProfileFragment()
    }

}
