package br.com.disapps.homepet.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.app.HomePet;
import br.com.disapps.homepet.ui.common.AppFragment;
import br.com.disapps.homepet.ui.custom.LoadingView;
import br.com.disapps.homepet.ui.profile.ProfileFragment;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by diefferson.santos on 31/08/17.
 */

public class LoginFragment extends AppFragment<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    private LoadingView loadingView;

    public static LoginFragment newInstance(){
        return new LoginFragment();
    }

    @Override
    public String getFragmentTag() {
        return LoginFragment.class.getSimpleName();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(HomePet.Companion.getInstance().getRestApi(), HomePet.Companion.getInstance().getPreferences());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAppActivityListener().setupLoadingActivity(new LoadingView());

        getAppActivityListener().setTitle("Entrar");
    }

    @Override
    public void showLoading(boolean cancelable) {
        if (loadingView != null) {
            loadingView.cancelableOnBackPressed(cancelable);
            loadingView.show(getFragmentManager(), loadingView.getViewTag());
        }
    }

    @Override
    public void dismissLoading() {
        if (loadingView != null) {
            loadingView.dismiss();
        }
    }

    @OnClick(R.id.login_bt)
    public void login(){
        if (validate()){
            getPresenter().login(username.getText().toString(), password.getText().toString());
        }
    }

    @Override
    public void loginSuccessfull(){
        getAppActivityListener().replaceFragment(ProfileFragment.newInstance());
    }

    private boolean validate() {
        boolean valid = true;

        if (TextUtils.isEmpty(username.getText().toString())) {
            valid = false;
            username.setError("Campo obrigatorio");
        }

        if (TextUtils.isEmpty(password.getText().toString())) {
            valid = false;
            password.setError("Campo obrigatorio");
        }

        return valid;
    }
}
