package br.com.disapps.homepet.data.cache

import br.com.disapps.homepet.data.ws.response.ApiListResponse
import br.com.disapps.homepet.data.ws.response.ApiSimpleResponse
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider

/**
* Created by diefferson.santos on 23/08/17.
*/

interface Providers{

     fun getHotels(apiRequest: Observable<ApiListResponse.ListHotelResponse>, key: DynamicKey, evictProvider: EvictProvider): Observable<ApiListResponse.ListHotelResponse>
     fun getHotel(apiRequest: Observable<ApiSimpleResponse.HotelResponse>, key: DynamicKey, evictProvider: EvictProvider): Observable<ApiSimpleResponse.HotelResponse>
     fun getComments(apiRequest: Observable<ApiListResponse.ListCommentResponse>, key: DynamicKey, evictProvider: EvictProvider): Observable<ApiListResponse.ListCommentResponse>
     fun getCoordinates(apiRequest: Observable<ApiSimpleResponse.CoordinateResponse>, key: DynamicKey, evictProvider: EvictProvider): Observable<ApiSimpleResponse.CoordinateResponse>

}
