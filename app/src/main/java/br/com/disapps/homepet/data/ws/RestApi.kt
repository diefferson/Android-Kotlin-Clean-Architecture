package br.com.disapps.homepet.data.ws

import br.com.disapps.homepet.data.ws.response.ApiSimpleResponse
import br.com.disapps.homepet.data.model.User
import br.com.disapps.homepet.data.ws.request.*
import br.com.disapps.homepet.data.ws.response.ApiListResponse
import br.com.disapps.homepet.data.ws.response.IncludeResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by diefferson.santos on 23/08/17.
 */

interface RestApi {

    @POST("auth")
    fun authLogin(@Header("Authorization") clientSecret: String, @Body request: PasswordLoginRequest): Observable<ApiSimpleResponse.AuthResponse>

    @POST("auth")
    fun refreshToken(@Header("Authorization") clientSecret: String, @Body request: RefreshTokenLoginRequest): Call<ApiSimpleResponse.AuthResponse>

    @POST("signup")
    fun signup(@Body request: SignupRequest): Observable<ApiSimpleResponse.SignUpResponse>

    @GET("user")
    fun getUser(@Header("Authorization") accessToken: String) : Observable<ApiSimpleResponse.UserResponse>

    @PATCH("user")
    fun patchUser(@Header("Authorization") accessToken: String, @Body user:User) : Observable<ApiSimpleResponse.UserResponse>

    @GET("hotel")
    fun getHotels(@Query("sort") sort: String?, @Query("sense") sense: String?): Observable<ApiListResponse.ListHotelResponse>

    @GET("hotel/{codeHotel}")
    fun getHotel(@Path("codeHotel") codeHotel: Int): Observable<ApiSimpleResponse.HotelResponse>

    @GET("comment/{codeHotel}")
    fun getComments(@Path("codeHotel") codeHotel: Int): Observable<ApiListResponse.ListCommentResponse>

    @POST("comment")
    fun postComment(@Header("Authorization") authorization: String, @Body request: IncludeCommentRequest): Observable<IncludeResponse>

    @POST("rating")
    fun postRating(@Header("Authorization") authorization: String,@Body request: IncludeRatingRequest): Observable<IncludeResponse>

    @GET("coordinates/{codeHotel}")
    fun getCoordinates(@Path("codeHotel") codeHotel: Int) : Observable<ApiSimpleResponse.CoordinateResponse>
}
