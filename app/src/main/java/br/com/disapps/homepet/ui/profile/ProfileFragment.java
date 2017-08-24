package br.com.disapps.homepet.ui.profile;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.ui.common.AppFragment;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class ProfileFragment extends AppFragment<IProfileView , ProfilePresenter> implements IProfileView {

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
        return new ProfilePresenter();
    }
}
