package br.com.disapps.homepet.ui.custom;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.disapps.homepet.R;

/**
 * Created by diefferson.santos on 04/09/17.
 */

public class LoadingView  extends DialogFragment {


    public static LoadingView newInstance() {
        return new LoadingView();
    }

    public String getViewTag() {
        return LoadingView.class.getSimpleName();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void cancelableOnBackPressed(boolean cancelable) {
        super.setCancelable(cancelable);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loading_view, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);

        return view;
    }
}