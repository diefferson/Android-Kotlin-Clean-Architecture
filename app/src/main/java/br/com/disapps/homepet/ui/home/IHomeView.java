package br.com.disapps.homepet.ui.home;

import java.util.List;

import br.com.disapps.homepet.data.model.Hotel;
import br.com.disapps.homepet.ui.common.AppView;

/**
 * Created by diefferson.santos on 23/08/17.
 */
public interface IHomeView  extends AppView {

    void fillHotelAdapter(List<Hotel> Hoteis);
}
