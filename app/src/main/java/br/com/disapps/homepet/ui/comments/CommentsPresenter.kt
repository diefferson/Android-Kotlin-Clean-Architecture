package br.com.disapps.homepet.ui.comments

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

import br.com.disapps.homepet.data.cache.HotelRepository
import br.com.disapps.homepet.data.ws.response.ApiListResponse
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by diefferson.santos on 31/08/17.
 */

class CommentsPresenter(private val mHotelRepository: HotelRepository) : MvpBasePresenter<ICommentsView>() {

    private val disposables = CompositeDisposable()

    fun loadComments(codeHotel: Int) {

        if (isViewAttached) {
            view.showLoading(false)
        }

        disposables.add(mHotelRepository.getComments(true, codeHotel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ApiListResponse.ListCommentResponse>() {
                    override fun onNext(response: ApiListResponse.ListCommentResponse) {

                        val comments = response.content

                        if (isViewAttached) {
                            view.fillComments(comments!!)
                            view.dismissLoading()
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (isViewAttached) {
                            IErrorHandlerHelper.defaultErrorResolver(this@CommentsPresenter.view, e)
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
