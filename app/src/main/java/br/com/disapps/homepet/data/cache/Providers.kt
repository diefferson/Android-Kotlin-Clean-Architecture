package br.com.disapps.homepet.data.cache

import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.data.ws.response.ApiListResponse
import io.reactivex.Observable
import io.rx_cache2.Actionable
import io.rx_cache2.DynamicKey
import io.rx_cache2.EvictProvider

/**
 * Created by diefferson.santos on 23/08/17.
 */

interface Providers{

    @Actionable
    abstract fun getHoteis(apiRequest: Observable<ApiListResponse<Hotel>>, key: DynamicKey, evictProvider: EvictProvider): Observable<ApiListResponse<Hotel>>
}
