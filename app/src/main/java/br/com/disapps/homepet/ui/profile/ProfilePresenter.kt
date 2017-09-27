package br.com.disapps.homepet.ui.profile

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

import br.com.disapps.homepet.data.prefs.Preferences

/**
 * Created by diefferson.santos on 23/08/17.
 */

class ProfilePresenter(private val mPreferences: Preferences) : MvpBasePresenter<IProfileView>() {

    fun logout() {
        mPreferences.clearUserPrefs()
        if (isViewAttached) {
            view.onLogout()
        }
    }

}
