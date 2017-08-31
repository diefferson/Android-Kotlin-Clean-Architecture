package br.com.disapps.homepet.ui.signup;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.ui.common.AppFragment;
import br.com.disapps.homepet.ui.login.ILoginView;

/**
 * Created by diefferson.santos on 31/08/17.
 */

public class SignupFragment extends AppFragment<ISignUpView, SignupPresenter> implements ISignUpView {

    public static  SignupFragment newInstance(){
        return  new SignupFragment();
    }

    @Override
    public String getFragmentTag() {
        return SignupFragment.class.getSimpleName();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_signup;
    }

    @Override
    public SignupPresenter createPresenter() {
        return new SignupPresenter();
    }
}
