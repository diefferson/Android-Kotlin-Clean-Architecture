package br.com.disapps.homepet.ui.home;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import br.com.disapps.homepet.data.cache.HomePetRepository;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.data.ws.response.ApiResponse;
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class HomePresenter extends MvpBasePresenter<IHomeView> {

    private final HomePetRepository mHomePetRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public HomePresenter(HomePetRepository homePetRepository){
        mHomePetRepository = homePetRepository;
    }

    public void loadHoteis(){
        disposables.add(mHomePetRepository.getHoteis(true)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ApiResponse<Hotel>>() {
                    @Override
                    public void onNext(ApiResponse<Hotel> response) {

                        List<Hotel> hoteis = response.getContent();

                        if(isViewAttached()){
                            getView().fillHotelAdapter(hoteis);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()){
                            IErrorHandlerHelper.defaultErrorResolver(HomePresenter.this.getView(), e);
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


    private ApiResponse<Hotel> generateHoteis(){

        Hotel hotel = new Hotel();
        hotel.setNome("Nome Hotel");
        hotel.setUrlImagemCapa("https://exp.cdn-hotels.com/hotels/1000000/150000/140600/140596/140596_275_z.jpg");

        List<Hotel> hoteis = new ArrayList<Hotel>();
        hoteis.add(hotel);
        hoteis.add(hotel);
        hoteis.add(hotel);
        hoteis.add(hotel);
        hoteis.add(hotel);

        return new ApiResponse<Hotel>("000", "suceso", hoteis);
    }
}
