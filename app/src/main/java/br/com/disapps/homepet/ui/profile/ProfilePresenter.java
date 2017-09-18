package br.com.disapps.homepet.ui.profile;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import br.com.disapps.homepet.data.prefs.Preferences;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class ProfilePresenter  extends MvpBasePresenter<IProfileView>{

    private final Preferences mPreferences;

    public ProfilePresenter(Preferences preferences){
        mPreferences = preferences;
    }

    public void logout(){
        mPreferences.clearUserPrefs();
        if(isViewAttached()){
            getView().onLogout();
        }
    }

}
