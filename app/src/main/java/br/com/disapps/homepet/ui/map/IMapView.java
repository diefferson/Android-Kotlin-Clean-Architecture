package br.com.disapps.homepet.ui.map;

import br.com.disapps.homepet.data.model.Coordinate;
import br.com.disapps.homepet.ui.common.AppView;

/**
 * Created by diefferson.santos on 23/08/17.
 */

public interface IMapView  extends AppView {

    void fillCoordinates(Coordinate coordinate);
}
