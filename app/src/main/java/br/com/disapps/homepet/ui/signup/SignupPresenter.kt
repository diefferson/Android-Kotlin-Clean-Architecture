package br.com.disapps.homepet.ui.signup

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

import br.com.disapps.homepet.data.prefs.Preferences
import br.com.disapps.homepet.data.ws.RestApi
import br.com.disapps.homepet.data.ws.request.SignupRequest
import br.com.disapps.homepet.data.ws.response.ApiSimpleResponse
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by diefferson.santos on 31/08/17.
 */

class SignupPresenter(private val restApi: RestApi, private val preferences: Preferences) : MvpBasePresenter<ISignUpView>() {

    private val disposables = CompositeDisposable()

    fun signup(name: String, email: String, password: String) {

        if (isViewAttached) {
            view.showLoading(false)
        }

        val request = SignupRequest(name, email, password)

        disposables.add(
                restApi.signup(request)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableObserver<ApiSimpleResponse.SignUpResponse>() {

                            override fun onNext(response: ApiSimpleResponse.SignUpResponse) {

                                if (isViewAttached) {
                                    view.dismissLoading()
                                    view.signupSucess()
                                }
                            }

                            override fun onError(e: Throwable) {
                                if (isViewAttached) {
                                    view.dismissLoading()
                                    IErrorHandlerHelper.defaultErrorResolver(this@SignupPresenter.view, e)
                                }
                            }

                            override fun onComplete() {

                            }
                        }))

    }
}
