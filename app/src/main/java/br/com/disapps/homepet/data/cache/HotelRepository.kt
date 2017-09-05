package br.com.disapps.homepet.data.cache

import android.content.Context
import br.com.disapps.homepet.data.prefs.Preferences
import br.com.disapps.homepet.data.ws.RestApi
import br.com.disapps.homepet.data.ws.response.CoordinateResponse
import br.com.disapps.homepet.data.ws.response.HotelResponse
import br.com.disapps.homepet.data.ws.response.ListCommentResponse
import br.com.disapps.homepet.data.ws.response.ListHotelResponse
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider
import java.io.File

/**
 * Created by diefferson.santos on 31/08/17.
 */
class HotelRepository (private val context: Context, preferences: Preferences, cacheDir: File, restApi: RestApi)
    : BaseRepository(preferences, cacheDir, restApi) {

    val KEY_HOTELS : String = "listHotels";
    val KEY_HOTEL : String = "hotel";
    val KEY_COMMENTS : String = "comments";
    val KEY_COORDINATES : String = "coordinates";

    fun getHoteis(hasInternetConnection: Boolean): Observable<ListHotelResponse> {
        return providers.getHoteis(
                restApi.getHoteis(),
                DynamicKey(KEY_HOTELS),
                EvictProvider(hasInternetConnection))
    }

    fun getHotel(hasInternetConnection: Boolean, codeHotel : Int): Observable<HotelResponse> {
        return providers.getHotel(
                restApi.getHotel(codeHotel),
                DynamicKey(KEY_HOTEL+codeHotel),
                EvictProvider(hasInternetConnection))
    }

    fun getComments(hasInternetConnection: Boolean, codeHotel : Int): Observable<ListCommentResponse> {
        return providers.getComments(
                restApi.getComments(codeHotel),
                DynamicKey(KEY_COMMENTS+codeHotel),
                EvictProvider(hasInternetConnection))
    }

    fun getCoordinates(hasInternetConnection: Boolean, codeHotel : Int): Observable<CoordinateResponse> {
        return providers.getCoordinates(
                restApi.getCoordinates(codeHotel),
                DynamicKey(KEY_COORDINATES+codeHotel),
                EvictProvider(hasInternetConnection))
    }
}