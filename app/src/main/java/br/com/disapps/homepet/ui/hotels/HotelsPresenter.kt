package br.com.disapps.homepet.ui.hotels

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

import br.com.disapps.homepet.data.cache.HotelRepository
import br.com.disapps.homepet.data.ws.request.HotelsRequest
import br.com.disapps.homepet.data.ws.response.ApiListResponse
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by diefferson.santos on 23/08/17.
 */

class HotelsPresenter(private val mHotelRepository: HotelRepository) : MvpBasePresenter<IHotelsView>() {

    private val disposables = CompositeDisposable()

    fun loadHoteis(request: HotelsRequest) {

        if (isViewAttached) {
            view.showLoading(false)
        }

        disposables.add(mHotelRepository.getHotels(true, request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ApiListResponse.ListHotelResponse>() {
                    override fun onNext(response: ApiListResponse.ListHotelResponse) {

                        val hoteis = response.content

                        if (isViewAttached) {
                            view.fillHotelAdapter(hoteis!!)
                            view.dismissLoading()
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (isViewAttached) {
                            IErrorHandlerHelper.defaultErrorResolver(this@HotelsPresenter.view, e)
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
