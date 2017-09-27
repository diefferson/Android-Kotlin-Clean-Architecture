package br.com.disapps.homepet.ui.hotel

import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.ui.common.AppView

/**
 * Created by diefferson.santos on 04/09/17.
 */

interface IHotelView : AppView {

    fun fillHeaderHotel(hotel: Hotel)
}
