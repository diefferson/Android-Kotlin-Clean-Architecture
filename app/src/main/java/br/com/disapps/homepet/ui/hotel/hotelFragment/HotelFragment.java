package br.com.disapps.homepet.ui.hotel.hotelFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.app.HomePet;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.ui.common.AppFragment;
import br.com.disapps.homepet.ui.hotel.adapter.HotelViewPagerAdapter;
import butterknife.BindView;

/**
 * Created by diefferson.santos on 03/09/17.
 */

public class HotelFragment extends AppFragment<IHotelView, HotelPresenter> implements IHotelView {

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private Hotel hotel;

    public static HotelFragment newInstance(int codeHotel){

        HotelFragment hotelFragment = new HotelFragment();

        Bundle args = new Bundle();
        args.putInt("codeHotel", codeHotel);
        hotelFragment.setArguments(args);

        return hotelFragment;
    }

    @Override
    public String getFragmentTag() {
        return HotelFragment.class.getSimpleName();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_hotel;
    }

    @Override
    public HotelPresenter createPresenter() {
        return new HotelPresenter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int codeHotel = getArguments().getInt("codeHotel");

        mViewPager.setAdapter(new HotelViewPagerAdapter(getChildFragmentManager(), getContext(), codeHotel));
        tabs.setupWithViewPager(mViewPager);
    }

}
