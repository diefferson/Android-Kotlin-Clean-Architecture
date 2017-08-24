package br.com.disapps.homepet.ui.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.app.HomePet;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.ui.common.AppFragment;
import br.com.disapps.homepet.ui.home.adapter.HotelAdapter;
import butterknife.BindView;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class HomeFragment extends AppFragment<IHomeView , HomePresenter> implements IHomeView   {

    @BindView(R.id.hotel_recycler)
    RecyclerView hotelRecycler;

    private HotelAdapter hotelAdapter;

    public static HomeFragment newInstance(){
        return  new HomeFragment();
    }

    @Override
    public String getFragmentTag() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(HomePet.Companion.getInstance().getHomePetRepository());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hotelRecycler.setHasFixedSize(true);
        hotelRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        getPresenter().loadHoteis();
    }

    @Override
    public void fillHotelAdapter(List<Hotel> hoteis) {
        hotelAdapter = new HotelAdapter(hoteis);
        hotelRecycler.setAdapter(hotelAdapter);
    }
}
