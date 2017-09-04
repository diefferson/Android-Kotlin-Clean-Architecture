package br.com.disapps.homepet.ui.comments;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import br.com.disapps.homepet.data.cache.HotelRepository;
import br.com.disapps.homepet.data.model.Comment;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.data.ws.response.HotelResponse;
import br.com.disapps.homepet.data.ws.response.ListCommentResponse;
import br.com.disapps.homepet.util.rx.IErrorHandlerHelper;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by diefferson.santos on 31/08/17.
 */

public class CommentsPresenter extends MvpBasePresenter<ICommentsView> {

    private final HotelRepository mHotelRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public CommentsPresenter(HotelRepository hotelRepository){
        mHotelRepository = hotelRepository;
    }

    public void loadComments(int codeHotel){

        disposables.add(mHotelRepository.getComments(false, codeHotel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<ListCommentResponse>() {
                    @Override
                    public void onNext(ListCommentResponse response) {

                        List<Comment> comments = response.getContent();

                        if(isViewAttached()){
                            getView().fillComments(comments);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(isViewAttached()){
                             IErrorHandlerHelper.defaultErrorResolver(CommentsPresenter.this.getView(), e);
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
