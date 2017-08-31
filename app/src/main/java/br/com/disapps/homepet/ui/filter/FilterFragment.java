package br.com.disapps.homepet.ui.filter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.ui.common.AppFragment;

/**
 * Created by diefferson.santos on 30/08/17.
 */

public class FilterFragment extends AppFragment<IFilterView, FilterPresenter> implements IFilterView {

    public static FilterFragment newInstance(){
        return new FilterFragment();
    }

    @Override
    public String getFragmentTag() {
        return FilterFragment.class.getSimpleName();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_filter;
    }

    @Override
    public FilterPresenter createPresenter() {
        return new FilterPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAppActivityListener().setTitle("Filtrar");
    }
}
