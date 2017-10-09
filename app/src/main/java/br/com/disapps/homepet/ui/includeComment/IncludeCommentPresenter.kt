package br.com.disapps.homepet.ui.includeComment

import br.com.disapps.homepet.data.prefs.Preferences
import br.com.disapps.homepet.data.ws.RestApi
import br.com.disapps.homepet.data.ws.request.IncludeCommentRequest
import br.com.disapps.homepet.data.ws.response.IncludeResponse
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


/**
 * Created by diefferson on 04/10/17.
 **/
class IncludeCommentPresenter(val restApi: RestApi, val preferences:Preferences):  MvpBasePresenter<IIncludeCommentView>(){

    private val disposables = CompositeDisposable()

    fun includeComment(codeHotel: Int, comment: String){

        var request = IncludeCommentRequest(codeHotel, comment)

        if(isViewAttached){
            view.showLoading(true)
        }

        disposables.add(restApi.postComment(preferences.authTokenWithPrefix, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<IncludeResponse>() {
                    override fun onNext(response: IncludeResponse) {

                        if(response.message.equals("Sucess")){
                            if (isViewAttached) {
                                view.fillSuccesInclude()
                                view.dismissLoading()
                            }
                        }else{
                            view.fillErrorInclude(response.message)
                            view.dismissLoading()
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (isViewAttached) {
                            IErrorHandlerHelper.defaultErrorResolver(this@IncludeCommentPresenter.view, e)
                            view.dismissLoading()
                        }
                    }

                    override fun onComplete() {

                    }
                }))


    }

    override fun detachView(retainInstance: Boolean) {
        disposables.clear()
    }
}