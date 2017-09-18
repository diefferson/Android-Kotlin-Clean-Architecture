package br.com.disapps.homepet.ui.profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.app.HomePet;
import br.com.disapps.homepet.ui.common.AppFragment;
import br.com.disapps.homepet.ui.login.LoginFragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class ProfileFragment extends AppFragment<IProfileView , ProfilePresenter> implements IProfileView {

    @BindView(R.id.dv_profile_avatar)
    SimpleDraweeView profileAvatar;


    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }

    @Override
    public String getFragmentTag() {
        return ProfileFragment.class.getSimpleName();
    }

    @Override
    public int getFragmentLayout() {

        return R.layout.fragment_profile;
    }

    @Override
    public ProfilePresenter createPresenter() {
        return new ProfilePresenter(HomePet.Companion.getInstance().getPreferences());
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        profileAvatar.setImageURI("https://lh3.googleusercontent.com/-8lC7WeHFujc/AAAAAAAAAAI/AAAAAAAAAAA/wKXtA6byhOs/s128-c-k/photo.jpg");
    }

    @OnClick(R.id.logout)
    public void logout(){
        getPresenter().logout();
    }

    @Override
    public void onLogout(){
        getAppActivityListener().replaceFragment(LoginFragment.newInstance());
    }
}
