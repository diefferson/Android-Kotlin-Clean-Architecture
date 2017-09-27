package br.com.disapps.homepet.ui.hotel

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter

import br.com.disapps.homepet.data.cache.HotelRepository
import br.com.disapps.homepet.data.ws.response.HotelResponse
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
 * Created by diefferson.santos on 04/09/17.
 **/

class HotelPresenter(private val mHotelRepository: HotelRepository) : MvpBasePresenter<IHotelView>() {
    private val disposables = CompositeDisposable()

    fun loadHotel(codeHotel: Int) {

        if (isViewAttached) {
            view.showLoading(false)
        }

        disposables.add(mHotelRepository.getHotel(true, codeHotel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<HotelResponse>() {
                    override fun onNext(response: HotelResponse) {

                        val hotel = response.content

                        if (isViewAttached) {
                            view.fillHeaderHotel(hotel!!)
                            view.dismissLoading()
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (isViewAttached) {
                            IErrorHandlerHelper.defaultErrorResolver(this@HotelPresenter.view, e)
                            view.dismissLoading()
                        }
                    }

                    override fun onComplete() {

                    }
                })
        )
    }

    override fun detachView(retainInstance: Boolean) {
        disposables.clear()
    }

}
