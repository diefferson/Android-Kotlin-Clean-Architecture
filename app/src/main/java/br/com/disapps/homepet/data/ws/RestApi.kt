package br.com.disapps.homepet.data.ws

import br.com.disapps.homepet.data.model.Auth
import br.com.disapps.homepet.data.model.Coordinate
import br.com.disapps.homepet.data.model.User
import br.com.disapps.homepet.data.ws.request.PasswordLoginRequest
import br.com.disapps.homepet.data.ws.request.RefreshTokenLoginRequest
import br.com.disapps.homepet.data.ws.request.SignupRequest
import br.com.disapps.homepet.data.ws.response.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

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
    abstract fun getHoteis(): Observable<ListHotelResponse>

    @GET("hotel/{codeHotel}")
    abstract fun getHotel(@Path("codeHotel") codeHotel: Int): Observable<HotelResponse>

    @GET("comment/{codeHotel}")
    abstract fun getComments(@Path("codeHotel") codeHotel: Int): Observable<ListCommentResponse>

    @GET("coordinates/{codeHotel}")
    abstract fun getCoordinates(@Path("codeHotel") codeHotel: Int) : Observable<CoordinateResponse>
}
