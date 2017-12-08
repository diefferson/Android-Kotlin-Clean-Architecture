package br.com.disapps.homepet.data.cache

import br.com.disapps.homepet.data.prefs.Preferences
import br.com.disapps.homepet.data.ws.RestApi
import br.com.disapps.homepet.data.ws.request.HotelsRequest
import br.com.disapps.homepet.data.ws.response.ApiListResponse
import br.com.disapps.homepet.data.ws.response.ApiSimpleResponse
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider
import java.io.File

/**
* Created by diefferson.santos on 31/08/17.
*/
class HotelRepository (preferences: Preferences, cacheDir: File, restApi: RestApi)
    : BaseRepository(preferences, cacheDir, restApi) {

    private val KEY_HOTELS : String = "listHotels"
    private val KEY_HOTEL : String = "hotel"
    private val KEY_COMMENTS : String = "comments"
    private val KEY_COORDINATES : String = "coordinates"

    fun getHotels(hasInternetConnection: Boolean, request: HotelsRequest): Observable<ApiListResponse.ListHotelResponse> {
        return providers.getHotels(
                restApi.getHotels(request.sort, request.sense),
                DynamicKey(KEY_HOTELS),
                EvictProvider(hasInternetConnection))
    }

    fun getHotel(hasInternetConnection: Boolean, codeHotel : Int): Observable<ApiSimpleResponse.HotelResponse> {
        return providers.getHotel(
                restApi.getHotel(codeHotel),
                DynamicKey(KEY_HOTEL+codeHotel),
                EvictProvider(hasInternetConnection))
    }

    fun getComments(hasInternetConnection: Boolean, codeHotel : Int): Observable<ApiListResponse.ListCommentResponse> {
        return providers.getComments(
                restApi.getComments(codeHotel),
                DynamicKey(KEY_COMMENTS+codeHotel),
                EvictProvider(hasInternetConnection))
    }

    fun getCoordinates(hasInternetConnection: Boolean, codeHotel : Int): Observable<ApiSimpleResponse.CoordinateResponse> {
        return providers.getCoordinates(
                restApi.getCoordinates(codeHotel),
                DynamicKey(KEY_COORDINATES+codeHotel),
                EvictProvider(hasInternetConnection))
    }
}