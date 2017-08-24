package br.com.disapps.homepet.ui.common;

import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by diefferson.santos on 23/08/17.
 */
public interface AppView extends MvpView, IErrorHandlerView{
    boolean hasInternetConnection();
    void showLoading();
    void dismissLoading();
}
