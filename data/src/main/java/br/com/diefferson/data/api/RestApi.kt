package br.com.diefferson.data.api

import br.com.diefferson.data.entity.User
import br.com.diefferson.data.entity.request.*
import br.com.diefferson.data.entity.response.ApiListResponse
import br.com.diefferson.data.entity.response.ApiSimpleResponse
import br.com.diefferson.data.entity.response.IncludeResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by dnso on 12/03/2018.
 */
interface RestApi {
    @POST("auth")
    fun authLogin(@Header("Authorization") clientSecret: String, @Body request: PasswordLoginRequest): Deferred<ApiSimpleResponse.AuthResponse>

    @POST("auth")
    fun refreshToken(@Header("Authorization") clientSecret: String, @Body request: RefreshTokenLoginRequest): Call<ApiSimpleResponse.AuthResponse>

    @POST("signup")
    fun signup(@Body request: SignupRequest): Deferred<ApiSimpleResponse.SignUpResponse>

    @GET("user")
    fun getUser(@Header("Authorization") accessToken: String) : Deferred<ApiSimpleResponse.UserResponse>

    @PATCH("user")
    fun patchUser(@Header("Authorization") accessToken: String, @Body user: User) : Deferred<ApiSimpleResponse.UserResponse>

    @GET("hotel")
    fun getHotels(@Query("sort") sort: String?, @Query("sense") sense: String?): Deferred<ApiListResponse.ListHotelResponse>

    @GET("hotel/{codeHotel}")
    fun getHotel(@Path("codeHotel") codeHotel: Int): Deferred<ApiSimpleResponse.HotelResponse>

    @GET("comment/{codeHotel}")
    fun getComments(@Path("codeHotel") codeHotel: Int): Deferred<ApiListResponse.ListCommentResponse>

    @POST("comment")
    fun postComment(@Header("Authorization") authorization: String, @Body request: IncludeCommentRequest): Deferred<IncludeResponse>

    @POST("rating")
    fun postRating(@Header("Authorization") authorization: String,@Body request: IncludeRatingRequest): Deferred<IncludeResponse>

    @GET("coordinates/{codeHotel}")
    fun getCoordinates(@Path("codeHotel") codeHotel: Int) : Deferred<ApiSimpleResponse.CoordinateResponse>
}