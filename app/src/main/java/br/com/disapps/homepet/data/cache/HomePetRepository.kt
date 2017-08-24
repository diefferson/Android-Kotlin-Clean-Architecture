package br.com.disapps.homepet.data.cache

import android.content.Context
import br.com.disapps.homepet.data.model.Hotel

import java.io.File

import br.com.disapps.homepet.data.prefs.Preferences
import br.com.disapps.homepet.data.ws.RestApi
import br.com.disapps.homepet.data.ws.response.ApiResponse
import io.reactivex.Observable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider

/**
 * Created by diefferson.santos on 23/08/17.
 */
class HomePetRepository(private val context: Context, preferences: Preferences, cacheDir: File, restApi: RestApi)
                : BaseRepository(preferences, cacheDir, restApi) {

    fun getHoteis(hasInternetConnection: Boolean): Observable<ApiResponse<Hotel>> {
        return providers.getHoteis(
                restApi.getHoteis(this.preferences.authTokenWithPrefix),
                DynamicKey(this.preferences.authTokenWithPrefix),
                EvictProvider(hasInternetConnection))
    }

}