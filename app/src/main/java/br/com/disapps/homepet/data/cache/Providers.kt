package br.com.disapps.homepet.data.cache

import br.com.disapps.homepet.data.ws.response.CoordinateResponse
import br.com.disapps.homepet.data.ws.response.ListCommentResponse
import br.com.disapps.homepet.data.ws.response.HotelResponse
import br.com.disapps.homepet.data.ws.response.ListHotelResponse
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider

/**
 * Created by diefferson.santos on 23/08/17.
 */

interface Providers{

    abstract fun getHoteis(apiRequest: Observable<ListHotelResponse>, key: DynamicKey, evictProvider: EvictProvider): Observable<ListHotelResponse>
    abstract fun getHotel(apiRequest: Observable<HotelResponse>, key: DynamicKey, evictProvider: EvictProvider): Observable<HotelResponse>
    abstract fun getComments(apiRequest: Observable<ListCommentResponse>, key: DynamicKey, evictProvider: EvictProvider): Observable<ListCommentResponse>
    abstract fun getCoordinates(apiRequest: Observable<CoordinateResponse>, key: DynamicKey, evictProvider: EvictProvider): Observable<CoordinateResponse>

}
