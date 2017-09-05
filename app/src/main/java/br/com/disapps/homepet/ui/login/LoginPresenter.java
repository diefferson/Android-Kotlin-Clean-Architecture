package br.com.disapps.homepet.ui.login;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import br.com.disapps.homepet.BuildConfig;
import br.com.disapps.homepet.data.model.Auth;
import br.com.disapps.homepet.data.prefs.Preferences;
import br.com.disapps.homepet.data.ws.RestApi;
import br.com.disapps.homepet.data.ws.request.PasswordLoginRequest;
import br.com.disapps.homepet.data.ws.response.ApiListResponse;
import br.com.disapps.homepet.data.ws.response.ApiSimpleResponse;
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by diefferson.santos on 31/08/17.
 */

public class LoginPresenter extends MvpBasePresenter<ILoginView> {

    private final CompositeDisposable disposables = new CompositeDisposable();
    private final RestApi restApi;
    private final Preferences preferences;

    public LoginPresenter(RestApi restApi, Preferences preferences) {
        this.restApi = restApi;
        this.preferences = preferences;
    }

    public void login(String username, String password){

        if(isViewAttached()){
            getView().showLoading(false);
        }

        PasswordLoginRequest request = new PasswordLoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        request.setGrantType("password");

        disposables.add(
                restApi.authLogin(BuildConfig.clientSecret, request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<ApiSimpleResponse<Auth>>() {

                            @Override
                            public void onNext(ApiSimpleResponse<Auth> response) {

                                preferences.saveAuth(response.getContent());

                                if (isViewAttached()) {
                                    getView().dismissLoading();
                                    getView().loginSuccessfull();
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                if (isViewAttached()) {
                                    getView().dismissLoading();
                                    IErrorHandlerHelper.defaultErrorResolver(LoginPresenter.this.getView(), e);
                                }
                            }

                            @Override
                            public void onComplete() {

                            }
                        }));

    }
}
