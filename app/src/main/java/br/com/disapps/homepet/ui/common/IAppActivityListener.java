package br.com.disapps.homepet.ui.common;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import br.com.disapps.homepet.ui.custom.LoadingView;

/**
 * Created by diefferson.santos on 23/08/17.
 */
public interface IAppActivityListener {

    void bindView();
    void setContainer(FrameLayout container);
    /**
     * Titulo para action bar
     * @param title
     */
    void setTitle(String title);
    /**
     * Titulo para action bar
     * @param title
     */
//    void setCenterTitle(String title);
    /**
     * Obtem a toolbar
     * @return
     */
//    Toolbar getToolbar();
    void setToolbar(Toolbar toolbar);
    /**
     * Troca o fragmento que herde AppFragment
     * @param fragment
     *
     * @see AppFragment
     *
     */
    void replaceFragment(AppFragment fragment);
    /**
     * Troca o fragmento que herde AppFragment e adiciona na Pilha de Voltar do FragmentManager
     * @param fragment
     *
     * @see AppFragment
     *
     */
    void replaceAndBackStackFragment(AppFragment fragment);


    boolean hasInternetConnection();

    void setupLoadingActivity(LoadingView loadingView);
    void showLoading(boolean cancelable);
    void dismissLoading();

//    void setAsChildView();
//    void setAsMainView();
}