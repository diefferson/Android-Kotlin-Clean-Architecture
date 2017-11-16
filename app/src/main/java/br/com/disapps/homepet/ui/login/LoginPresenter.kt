package br.com.disapps.homepet.ui.login

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

import br.com.disapps.homepet.BuildConfig
import br.com.disapps.homepet.data.model.Auth
import br.com.disapps.homepet.data.prefs.Preferences
import br.com.disapps.homepet.data.ws.RestApi
import br.com.disapps.homepet.data.ws.request.PasswordLoginRequest
import br.com.disapps.homepet.data.ws.response.ApiListResponse
import br.com.disapps.homepet.data.ws.response.ApiSimpleResponse
import br.com.disapps.homepet.data.ws.response.AuthResponse
import br.com.disapps.homepet.data.ws.response.UserResponse
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by diefferson.santos on 31/08/17.
 */

class LoginPresenter(private val restApi: RestApi, private val preferences: Preferences) : MvpBasePresenter<ILoginView>() {

    private val disposables = CompositeDisposable()

    fun login(username: String, password: String) {

        if (isViewAttached) {
            view.showLoading(false)
        }

        val request = PasswordLoginRequest()
        request.username = username
        request.password = password
        request.grantType = "password"

        disposables.add(
                restApi.authLogin(BuildConfig.clientSecret, request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableObserver<AuthResponse>() {

                            override fun onNext(response: AuthResponse) {
                                preferences.saveAuth(response.content!!)
                                getUser()
                            }

                            override fun onError(e: Throwable) {
                                if (isViewAttached) {
                                    view.dismissLoading()
                                    IErrorHandlerHelper.defaultErrorResolver(this@LoginPresenter.view, e)
                                }
                            }

                            override fun onComplete() {

                            }
                        }))



    }

    private fun getUser() {

        disposables.add(
            restApi.getUser(preferences.authTokenWithPrefix)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<UserResponse>() {

                override fun onNext(response: UserResponse) {
                    preferences.saveUser(response.content!!)

                    if (isViewAttached) {
                        view.dismissLoading()
                        view.loginSuccessfull()
                    }
                }

                override fun onError(e: Throwable) {
                    if (isViewAttached) {
                        view.dismissLoading()
                        IErrorHandlerHelper.defaultErrorResolver(this@LoginPresenter.view, e)
                    }
                }

                override fun onComplete() {

                }
            }))

    }
}
