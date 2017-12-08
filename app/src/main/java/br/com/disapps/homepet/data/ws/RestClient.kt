package br.com.disapps.homepet.data.ws

import android.util.Log
import br.com.disapps.homepet.BuildConfig
import br.com.disapps.homepet.app.HomePet
import br.com.disapps.homepet.data.ws.request.RefreshTokenLoginRequest
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

/**
* Created by diefferson.santos on 23/08/17.
*/
class RestClient {

    val api: RestApi

    private var httpClient: OkHttpClient? = null

    private val retrofitClient: Retrofit

    companion object {
        val AUTHORIZATION_HEADER_PREFIX = "Bearer "
    }

    init {

        if (BuildConfig.HTTP_LOG_ENABLED) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            httpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpInterceptor())
                    .addInterceptor(loggingInterceptor)
                    .build()
        } else {
            httpClient = OkHttpClient.Builder()
                    .addInterceptor(HttpInterceptor())
                    .build()
        }


        retrofitClient = Retrofit.Builder()
                .baseUrl(BuildConfig.HOST + "/")
                .client(httpClient!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        api = retrofitClient.create(RestApi::class.java)
    }

    //    /* https://gist.github.com/alex-shpak/da1e65f52dc916716930 */
    private inner class HttpInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()

            //Build new request
            val builder = request.newBuilder()

            val token = HomePet.instance?.preferences?.auth?.accessToken //save token of this request for future

            request = builder.build() //overwrite old request
            val response = chain.proceed(request) //perform request, here original request will be executed

            if (response.code() == 498 || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                synchronized(httpClient!!) {
                    //perform all 401 in sync blocks, to avoid multiply token updates


                    val currentToken = HomePet.instance?.preferences?.auth?.accessToken //get currently stored token

                    if (currentToken != null && currentToken == token) { //compare current token with token that was stored before, if it was not updated - do update

                        val code = refreshToken() / 100 //refresh token
                        if (code != 2) { //if refresh token failed for some reason
                            if (code == 4)
                            //only if response is 400, 500 might mean that token was not updated
                                logout() //go to login screen
                            return response //if token refresh failed - show error to user
                        }

                    }

                    if (HomePet.instance?.preferences?.authTokenWithPrefix != null) { //retry requires new auth token,
                        setAuthHeader(builder, HomePet.instance?.preferences?.auth?.accessToken) //set auth token to updated
                        request = builder.build()
                        return chain.proceed(request) //repeat request with new token
                    }
                }
            }

            return response
        }


        private fun setAuthHeader(builder: Request.Builder, token: String?) {
            if (token != null)
                builder.header("Authorization", token)
        }

        @Synchronized private fun refreshToken(): Int {

            try {

                val request =  RefreshTokenLoginRequest()
                request.grantType = "refresh_token"
                request.refreshToken = HomePet.instance?.preferences?.auth?.refreshToken!!

                val call = api.refreshToken(BuildConfig.clientSecret, request )
                val refresh = call.execute()

                if (refresh.isSuccessful) {
                    HomePet.instance?.preferences?.saveAuth(refresh.body()?.content!!)
                } else {
                    Log.e(RestClient::class.java.simpleName, "refresh token call failed!")
                    logout()
                }

                return refresh.code()

            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: StackOverflowError) {
                e.printStackTrace()
            }

            return HttpsURLConnection.HTTP_BAD_REQUEST
        }

        private fun logout(): Int {
            HomePet.instance?.preferences?.clearUserPrefs()
            return 0
        }

    }
}
