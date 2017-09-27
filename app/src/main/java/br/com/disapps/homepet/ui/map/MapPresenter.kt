package br.com.disapps.homepet.ui.map

import br.com.disapps.homepet.data.cache.HotelRepository
import br.com.disapps.homepet.data.ws.response.CoordinateResponse
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

/**
* Created by diefferson.santos on 23/08/17.
**/
class MapPresenter(private val mHotelRepository: HotelRepository) : MvpBasePresenter<IMapView>() {
    private val disposables = CompositeDisposable()

    fun loadCoordinates(codeHotel: Int) {

        if (isViewAttached) {
            view.showLoading(false)
        }

        disposables.add(mHotelRepository.getCoordinates(true, codeHotel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CoordinateResponse>() {
                    override fun onNext(response: CoordinateResponse) {

                        val coordinates = response.content

                        if (isViewAttached) {
                            view.fillCoordinates(coordinates!!)
                            view.dismissLoading()
                        }
                    }

                    override fun onError(e: Throwable) {
                        if (isViewAttached) {
                            IErrorHandlerHelper.defaultErrorResolver(this@MapPresenter.view, e)
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
