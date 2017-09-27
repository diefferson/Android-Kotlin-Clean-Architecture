package br.com.disapps.homepet.ui.details

import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.ui.common.AppView

/**
 * Created by diefferson.santos on 31/08/17.
 */

interface IHotelDetailsView : AppView {

    fun fillHotelDetails(hotel: Hotel)
}
