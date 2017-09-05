package br.com.disapps.homepet.ui.hotel;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import br.com.disapps.homepet.data.cache.HotelRepository;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.data.ws.response.HotelResponse;
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by diefferson.santos on 04/09/17.
 */

public class HotelPresenter  extends MvpBasePresenter<IHotelView> {

    private final HotelRepository mHotelRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public HotelPresenter(HotelRepository hotelRepository) {
        mHotelRepository = hotelRepository;
    }

    public void loadHotel(int codeHotel) {

        if(isViewAttached()){
            getView().showLoading(false);
        }

        disposables.add(mHotelRepository.getHotel(true, codeHotel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<HotelResponse>() {
                @Override
                public void onNext(HotelResponse response) {

                    Hotel hotel = response.getContent();

                    if (isViewAttached()) {
                        getView().fillHeaderHotel(hotel);
                        getView().dismissLoading();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (isViewAttached()) {
                         IErrorHandlerHelper.defaultErrorResolver(HotelPresenter.this.getView(), e);
                         getView().dismissLoading();
                    }
                }

                @Override
                public void onComplete() {

                }
            })
        );
    }

    @Override
    public void detachView(boolean retainInstance) {
        disposables.clear();
    }

}
