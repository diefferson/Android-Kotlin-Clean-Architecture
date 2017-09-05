package br.com.disapps.homepet.ui.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import java.util.List;

import br.com.disapps.homepet.receiver.NetworkChangeReceiver;
import br.com.disapps.homepet.ui.custom.LoadingView;
import br.com.disapps.homepet.util.rx.RxHttpError;
import butterknife.ButterKnife;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public abstract class AppActivity extends AppCompatActivity implements IAppActivityListener, IErrorHandlerView, AppView {

    FrameLayout container;
    private LoadingView loadingView;

    private NetworkChangeReceiver networkReceiver;
    private List<InternetConnectionListener> internetListenerList;

    public void bindView(){
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBroadcastReceiver(true);
    }

    public void setupLoadingActivity(LoadingView loadingView) {
        this.loadingView = loadingView;
    }

    public void showLoading(boolean cancelable) {
        if (loadingView != null) {
            loadingView.cancelableOnBackPressed(cancelable);
            loadingView.show(getSupportFragmentManager(), loadingView.getViewTag());
        }
    }

    public void dismissLoading() {
        if (loadingView != null) {
            loadingView.dismiss();
        }
    }


    @Override
    public void setContainer(FrameLayout container){
        this.container = container;
    }

    @Override
    public void replaceFragment(AppFragment fragment) {
        FragmentTransaction ft = getFragmentTransaction();
        ft.replace(container.getId(), fragment, fragment.getFragmentTag());
        ft.commit();
    }

    @Override
    public void replaceAndBackStackFragment(AppFragment fragment) {
        FragmentTransaction ft = getFragmentTransaction();
        ft.replace(container.getId(), fragment, fragment.getFragmentTag());
        ft.addToBackStack(fragment.getFragmentTag());
        ft.commit();
    }

    private FragmentTransaction getFragmentTransaction() {
        return getSupportFragmentManager().beginTransaction();
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean hasInternetConnection() {
        return networkReceiver.isOnline();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupBroadcastReceiver(true);
    }

    @Override
    protected void onPause() {
        setupBroadcastReceiver(false);
        super.onPause();
    }


    @Override
    public void error(RxHttpError error) {

    }

    @Override
    public void error(Throwable e) {

    }

    public void setupBroadcastReceiver(boolean on) {
        if (networkReceiver == null)
            networkReceiver = new NetworkChangeReceiver();

        if (on) {
            registerReceiver(networkReceiver, new IntentFilter(NetworkChangeReceiver.NOTIFY_NETWORK_CHANGE));

            IntentFilter customFilter = new IntentFilter(NetworkChangeReceiver.NOTIFY_NETWORK_CHANGE);
            LocalBroadcastManager.getInstance(this).registerReceiver(mLocalReceiver, customFilter);
        } else {
            unregisterReceiver(networkReceiver);
        }
    }

    private BroadcastReceiver mLocalReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean internetConnection = intent.getBooleanExtra(NetworkChangeReceiver.EXTRA_IS_CONNECTED, false);

            if (internetListenerList != null && !internetListenerList.isEmpty()) {
                for (InternetConnectionListener listener : internetListenerList) {
                    listener.onInternetConnectionChange(internetConnection);
                }
            }
        }
    };


}