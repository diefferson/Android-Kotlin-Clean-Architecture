package br.com.disapps.homepet.ui.hotels;

import java.util.List;

import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.ui.common.AppView;

/**
 * Created by diefferson.santos on 23/08/17.
 */
public interface IHotelsView extends AppView {

    void fillHotelAdapter(List<Hotel> Hoteis);
}
