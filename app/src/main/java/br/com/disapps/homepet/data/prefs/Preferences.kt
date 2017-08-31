package br.com.disapps.homepet.data.prefs

import android.content.Context
import android.content.SharedPreferences

import br.com.disapps.homepet.data.model.Auth
import br.com.disapps.homepet.data.ws.RestClient

/**
 * Created by diefferson.santos on 23/08/17.
 */
class Preferences(context: Context) {

    private val mPreferences: SharedPreferences

    companion object {
        val USER_PREFERENCES = "br.com.disapps.homepet.user_preferences"
        val AUTH_KEY = "auth-key"
        val AUTH_RENEW = "auth-renew"
        val AUTH_EXPIRES_AT_KEY = "auth-expires_at"
        val AUTH_KEY_TYPE = "auth-key_type"
        val PROFILE_UUID_KEY = "profile-uuid"
        val PROFILE_NAME_KEY = "profile-name"
    }

    init {
        mPreferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
    }

    val isLogged: Boolean
        get() = mPreferences.contains(AUTH_KEY) && !mPreferences.getString(AUTH_KEY, "")!!.isEmpty()

    val auth: Auth
        get() {
            val auth = Auth(    mPreferences.getString(AUTH_KEY, ""),
                                mPreferences.getString(AUTH_KEY_TYPE, ""),
                                mPreferences.getString(AUTH_EXPIRES_AT_KEY, ""),
                                mPreferences.getString(AUTH_RENEW, "")
                            )
            return auth
        }

    val authTokenWithPrefix: String
        get() = RestClient.AUTHORIZATION_HEADER_PREFIX + mPreferences.getString(AUTH_KEY, "")!!

    val uuid: String
        get() = mPreferences.getString(PROFILE_UUID_KEY, "")

    fun clearUserPrefs() {
        mPreferences.edit().clear().apply()
    }

    fun saveAuth(auth: Auth) {
        mPreferences.edit()
                .putString(AUTH_KEY, auth.accessToken)
                .putString(AUTH_RENEW, auth.refreshToken)
                .putString(AUTH_EXPIRES_AT_KEY, auth.expiresIn)
                .putString(AUTH_KEY_TYPE, auth.tokenType)
                .apply()
    }


}
