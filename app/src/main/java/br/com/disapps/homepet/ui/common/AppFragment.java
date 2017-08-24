package br.com.disapps.homepet.ui.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;

import java.net.HttpURLConnection;

import br.com.disapps.homepet.util.rx.RxHttpError;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public abstract class AppFragment<V extends AppView, P extends MvpPresenter<V>> extends MvpFragment<V, P> implements IErrorHandlerView, AppView {

    private IAppActivityListener iAppActivityListener;
    private Unbinder unbinder;

    @Override
    public boolean hasInternetConnection() {
        return getAppActivityListener().hasInternetConnection();
    }
    public void showLoading() {
//        if (loadingFragment == null) {
//            loadingFragment = LoadingFragment.newInstance();
//        }
//        loadingFragment.show(getFragmentManager(), LoadingFragment.class.getSimpleName());
    }

    public void dismissLoading() {
//        loadingFragment.dismiss();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        iAppActivityListener = (IAppActivityListener) context;
    }

    @Override
    public void error(RxHttpError error) {
        switch (error.errorCode) {
            case RxHttpError.SOCKETTIMEOUT_CODE:
            case RxHttpError.UNKNOWNHOST_CODE:
//                setMessageContent(true, R.string.err_unknownhost, getSectionIcPlaceholder());
                break;
            case RxHttpError.NO_CONNECTIVITY_CODE:
//                setMessageContent(true, R.string.err_connectivity, getSectionIcPlaceholder());
                break;
            case HttpURLConnection.HTTP_UNAUTHORIZED:
//                CustomApplication.getInstance().doLogout( getContext() );
                break;
            case 422:
//                setMessageContent(true, error.detail, getSectionIcPlaceholder());
                break;
            default:
//                setMessageContent(true, R.string.err_default, getSectionIcPlaceholder());
                break;
        }
    }

    @Override
    public void error(Throwable e) {
        e.printStackTrace();
//        setMessageContent(true, R.string.err_default, getSectionIcPlaceholder());
    }

    protected void setMessageContent(boolean isVisible, @StringRes int resMessage, @DrawableRes int resIcon) {
//        setMessageContent(isVisible, getContext().getString(resMessage), resIcon);
    }


    /**
     * Tag para identificacao do Fragmento na pilha do FragmentManager
     *
     * @return Tag que identifica o fragmento
     */
    public abstract String getFragmentTag();

    public abstract int getFragmentLayout();

    protected IAppActivityListener getAppActivityListener() {
        return iAppActivityListener;
    }
}
