package br.com.disapps.homepet.ui.hotels

import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.ui.common.AppView

/**
 * Created by diefferson.santos on 23/08/17.
 */
interface IHotelsView : AppView {

    fun fillHotelAdapter(Hoteis: List<Hotel>)
}
