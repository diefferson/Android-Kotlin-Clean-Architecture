package br.com.diefferson.data.api

import android.util.Log
import br.com.diefferson.data.BuildConfig
import br.com.diefferson.data.entity.request.RefreshTokenLoginRequest
import br.com.diefferson.data.storage.preferences.Preferences
import br.com.disapps.data.api.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection

/**
 * Created by diefferson.santos on 23/08/17.
 */
class RestClient(val preferences : Preferences) {

    companion object {
        const val AUTHORIZATION_HEADER_PREFIX = "Bearer "
    }

    val api: RestApi

    private var httpClientBuilder: OkHttpClient.Builder
    private var httpClient: OkHttpClient
    private val retrofitClient: Retrofit

    init {

        httpClientBuilder = OkHttpClient.Builder()
                .addInterceptor(HttpInterceptor())

        if (BuildConfig.HTTP_LOG_ENABLED) {

            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            httpClientBuilder.addInterceptor(loggingInterceptor)
        }

        httpClient = httpClientBuilder.build()

        retrofitClient = Retrofit.Builder()
                .baseUrl(BuildConfig.HOST + "/")
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

        api = retrofitClient.create(RestApi::class.java)
    }

    /* https://gist.github.com/alex-shpak/da1e65f52dc916716930 */
    private inner class HttpInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
            var request = chain.request()

            //Build new request
            val builder = request.newBuilder()

            val token = preferences.auth.accessToken //save token of this request for future

            request = builder.build() //overwrite old request
            val response = chain.proceed(request) //perform request, here original request will be executed

            if (response.code() == 498 || response.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                synchronized(httpClient) {
                    //perform all 401 in sync blocks, to avoid multiply token updates

                    val currentToken = preferences.auth.accessToken //get currently stored token

                    if (currentToken.isNotEmpty() && currentToken == token) { //compare current token with token that was stored before, if it was not updated - do update

                        val code = refreshToken() / 100 //refresh token
                        if (code != 2) { //if refresh token failed for some reason
                            if (code == 4)
                            //only if response is 400, 500 might mean that token was not updated
                                logout() //go to login screen
                            return response //if token refresh failed - show error to user
                        }

                    }

                    if (preferences.authTokenWithPrefix.isNotEmpty()) { //retry requires new auth token,
                        setAuthHeader(builder, preferences.auth.accessToken) //set auth token to updated
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
                request.refreshToken = preferences.auth.refreshToken

                val call = api.refreshToken(BuildConfig.clientSecret, request )
                val refresh = call.execute()

                if (refresh.isSuccessful) {
                    preferences.saveAuth(refresh.body()?.content!!)
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
            preferences.clearUserPrefs()
            return 0
        }
    }
}
