package br.com.disapps.homepet.data.ws

import br.com.disapps.homepet.data.model.Auth
import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.data.ws.response.ApiResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

/**
 * Created by diefferson.santos on 23/08/17.
 */

interface RestApi {

    @GET("hoteis")
    abstract fun getHoteis(@Header("Authorization") accessToken: String): Observable<ApiResponse<Hotel>>

}
