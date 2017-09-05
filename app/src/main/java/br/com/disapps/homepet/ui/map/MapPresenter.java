package br.com.disapps.homepet.ui.map;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import br.com.disapps.homepet.data.cache.HotelRepository;
import br.com.disapps.homepet.data.model.Coordinate;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.data.ws.response.CoordinateResponse;
import br.com.disapps.homepet.data.ws.response.HotelResponse;
import br.com.disapps.homepet.ui.hotel.HotelPresenter;
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class MapPresenter extends MvpBasePresenter<IMapView> {


    private final HotelRepository mHotelRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public MapPresenter(HotelRepository hotelRepository) {
        mHotelRepository = hotelRepository;
    }

    public void loadCoordinates(int codeHotel) {

        if(isViewAttached()){
            getView().showLoading(false);
        }

        disposables.add(mHotelRepository.getCoordinates(true, codeHotel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(new DisposableObserver<CoordinateResponse>() {
                @Override
                public void onNext(CoordinateResponse response) {

                    Coordinate coordinates = response.getContent();

                    if (isViewAttached()) {
                        getView().fillCoordinates(coordinates);
                        getView().dismissLoading();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if (isViewAttached()) {
                        IErrorHandlerHelper.defaultErrorResolver(MapPresenter.this.getView(), e);
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
