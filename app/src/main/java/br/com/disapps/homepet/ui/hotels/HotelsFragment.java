package br.com.disapps.homepet.ui.hotels;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.app.HomePet;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.ui.common.AppFragment;
import br.com.disapps.homepet.ui.filter.FilterFragment;
import br.com.disapps.homepet.ui.hotels.adapter.HotelAdapter;
import butterknife.BindView;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public class HotelsFragment extends AppFragment<IHotelsView , HotelsPresenter> implements IHotelsView   {

    @BindView(R.id.hotel_recycler)
    RecyclerView hotelRecycler;

    private HotelAdapter hotelAdapter;

    public static HotelsFragment newInstance(){
        return  new HotelsFragment();
    }

    @Override
    public String getFragmentTag() {
        return HotelsFragment.class.getSimpleName();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_hotels;
    }

    @Override
    public HotelsPresenter createPresenter() {
        return new HotelsPresenter(HomePet.Companion.getInstance().getHoteltRepository());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        getAppActivityListener().setTitle("Pesquisar");

        hotelRecycler.setHasFixedSize(true);
        hotelRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        getPresenter().loadHoteis();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.hotels_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter_hotel){
            getAppActivityListener().replaceAndBackStackFragment(FilterFragment.newInstance());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void fillHotelAdapter(List<Hotel> hoteis) {
        hotelAdapter = new HotelAdapter(hoteis);
        hotelRecycler.setAdapter(hotelAdapter);
    }
}
