package br.com.disapps.homepet.data.prefs

import android.content.Context
import android.content.SharedPreferences

import br.com.disapps.homepet.data.model.Auth
import br.com.disapps.homepet.data.model.User
import br.com.disapps.homepet.data.ws.RestClient
import java.util.*

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
        val USER_CODE_KEY = "profile-code"
        val USER_NAME_KEY = "profile-name"
        val USER_EMAIL_KEY = "profile-email"
        val USER_ADDRESS_KEY = "profile-address"
        val USER_PHONE_KEY = "profile-phone"
        val USER_AVATAR_KEY = "profile-avatar"
        val USER_SEX_KEY = "profile-sex"
        val USER_BIRTHDAY_KEY = "profile-birthday"
        val USER_CEP_KEY = "profile-cep"
        val USER_CITY_KEY = "profile-city"
        val USER_UF_KEY = "profile-uf"
        val USER_STATUS_KEY = "profile-status"
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
        get() = mPreferences.getString(USER_CODE_KEY, "")

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

    fun saveUser(user: User){

        mPreferences.edit()
                .putInt(USER_CODE_KEY, user.code!!)
                .putString(USER_NAME_KEY, user.name)
                .putString(USER_EMAIL_KEY, user.email)
                .putString(USER_ADDRESS_KEY, user.address)
                .putString(USER_PHONE_KEY, user.phone)
                .putString(USER_AVATAR_KEY, user.avatar)
                .putString(USER_SEX_KEY, user.sex)
                .putLong(USER_BIRTHDAY_KEY, user.birthday!!.time)
                .putInt(USER_CEP_KEY, user.cep!!)
                .putString(USER_CITY_KEY, user.city)
                .putString(USER_UF_KEY, user.uf)
                .putString(USER_STATUS_KEY, user.status)
                .apply()
    }

    fun getUser() : User{

        var user = User()

        user.code = mPreferences.getInt(USER_CODE_KEY, 0)
        user.name = mPreferences.getString(USER_NAME_KEY, "")
        user.email = mPreferences.getString(USER_EMAIL_KEY, "")
        user.address = mPreferences.getString(USER_ADDRESS_KEY, "")
        user.phone = mPreferences.getString(USER_PHONE_KEY, "")
        user.avatar = mPreferences.getString(USER_AVATAR_KEY, "")
        user.sex = mPreferences.getString(USER_SEX_KEY, "")
        user.birthday = Date(mPreferences.getLong(USER_BIRTHDAY_KEY, 0))
        user.cep = mPreferences.getInt(USER_CEP_KEY, 0)
        user.city = mPreferences.getString(USER_CITY_KEY, "")
        user.uf = mPreferences.getString(USER_UF_KEY, "")
        user.status = mPreferences.getString(USER_STATUS_KEY, "")

        return  user
    }

}
