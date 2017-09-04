package br.com.disapps.homepet.ui.details;

import br.com.disapps.homepet.R;
import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.ui.common.AppFragment;
import br.com.disapps.homepet.ui.common.AppView;

/**
 * Created by diefferson.santos on 31/08/17.
 */

public class HotelDetailsFragment extends AppFragment<IHotelDetailsView, HotelDetailsPresenter> implements AppView {

    public static HotelDetailsFragment newInstance(){
        return new HotelDetailsFragment();
    }

    @Override
    public String getFragmentTag() {
        return HotelDetailsFragment.class.getSimpleName();
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_hotel_details;
    }

    @Override
    public HotelDetailsPresenter createPresenter() {
        return new HotelDetailsPresenter();
    }
}
