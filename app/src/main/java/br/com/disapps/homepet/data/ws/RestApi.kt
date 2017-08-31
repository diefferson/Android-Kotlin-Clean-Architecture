package br.com.disapps.homepet.data.ws

import br.com.disapps.homepet.data.model.Auth
import br.com.disapps.homepet.data.model.Hotel
import br.com.disapps.homepet.data.model.User
import br.com.disapps.homepet.data.ws.request.PasswordLoginRequest
import br.com.disapps.homepet.data.ws.request.RefreshTokenLoginRequest
import br.com.disapps.homepet.data.ws.request.SignupRequest
import br.com.disapps.homepet.data.ws.response.ApiListResponse
import br.com.disapps.homepet.data.ws.response.ApiSimpleResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * Created by diefferson.santos on 23/08/17.
 */

interface RestApi {

    @POST("auth")
    abstract fun authLogin(@Header("Authorization") clientSecret: String, @Body request: PasswordLoginRequest): Observable<ApiSimpleResponse<Auth>>

    @POST("auth")
    abstract fun refreshToken(@Header("Authorization") clientSecret: String, @Body request: RefreshTokenLoginRequest): Call<ApiSimpleResponse<Auth>>

    @POST("signup")
    abstract fun signup(@Body request: SignupRequest): Observable<ApiSimpleResponse<String>>

    @GET("user")
    abstract fun getUser(@Header("Authorization") accessToken: String) : Observable<ApiSimpleResponse<User>>

    @GET("hotel")
    abstract fun getHoteis(): Observable<ApiListResponse<Hotel>>

}
