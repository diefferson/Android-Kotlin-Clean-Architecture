package br.com.disapps.homepet.ui.hotels;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import br.com.disapps.homepet.data.cache.HomePetRepository;
import br.com.disapps.homepet.data.cache.HotelRepository;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.data.ws.response.ApiListResponse;
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper;
import io.reactivex.Observable;
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
        disposables.add(mHotelRepository.getHoteis(getView().hasInternetConnection())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ApiListResponse<Hotel>>() {
                    @Override
                    public void onNext(ApiListResponse<Hotel> response) {

                        List<Hotel> hoteis = response.getContent();

                        if(isViewAttached()){
                            getView().fillHotelAdapter(hoteis);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()){
                            IErrorHandlerHelper.defaultErrorResolver(HotelsPresenter.this.getView(), e);
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


    private ApiListResponse<Hotel> generateHoteis(){

        Hotel hotel = new Hotel();
        hotel.setName("Nome Hotel");
        hotel.setCoverImage("https://exp.cdn-hotels.com/hotels/1000000/150000/140600/140596/140596_275_z.jpg");
        hotel.setRating(4.3F);
        hotel.setRatingsNumber(53);

        List<Hotel> hoteis = new ArrayList<Hotel>();
        hoteis.add(hotel);
        hoteis.add(hotel);
        hoteis.add(hotel);
        hoteis.add(hotel);
        hoteis.add(hotel);

        return new ApiListResponse<Hotel>( "suceso", null, null, null, hoteis);
    }
}
