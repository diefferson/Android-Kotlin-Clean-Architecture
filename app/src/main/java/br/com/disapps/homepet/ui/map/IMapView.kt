package br.com.disapps.homepet.ui.map

import br.com.disapps.homepet.data.model.Coordinate
import br.com.disapps.homepet.ui.common.AppView

/**
* Created by diefferson.santos on 23/08/17.
**/
interface IMapView : AppView {

    fun fillCoordinates(coordinate: Coordinate)
}
