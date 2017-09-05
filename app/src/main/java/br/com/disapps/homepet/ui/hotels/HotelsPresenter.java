package br.com.disapps.homepet.ui.hotels;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import br.com.disapps.homepet.data.cache.HotelRepository;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.data.ws.response.ListHotelResponse;
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class HotelsPresenter extends MvpBasePresenter<IHotelsView> {

    private final HotelRepository mHotelRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public HotelsPresenter(HotelRepository hotelRepository){
        mHotelRepository = hotelRepository;
    }

    public void loadHoteis(){

        if(isViewAttached()){
            getView().showLoading(false);
        }

        disposables.add(mHotelRepository.getHoteis(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ListHotelResponse>() {
                    @Override
                    public void onNext(ListHotelResponse response) {

                        List<Hotel> hoteis = response.getContent();

                        if(isViewAttached()){
                            getView().fillHotelAdapter(hoteis);
                            getView().dismissLoading();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()){
                            IErrorHandlerHelper.defaultErrorResolver(HotelsPresenter.this.getView(), e);
                            getView().dismissLoading();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void detachView(boolean retainInstance) {
        disposables.clear();
    }

}
