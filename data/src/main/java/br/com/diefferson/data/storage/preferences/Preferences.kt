package br.com.diefferson.data.storage.preferences

import android.content.Context
import android.content.SharedPreferences
import br.com.diefferson.data.entity.Auth
import br.com.diefferson.data.entity.User
import br.com.diefferson.data.api.RestClient
import java.util.*

/**
* Created by diefferson.santos on 23/08/17.
*/
class Preferences(context: Context) {

    private val mPreferences: SharedPreferences

    companion object {
        private const val USER_PREFERENCES = "br.com.disapps.homepet.user_preferences"
        private const val AUTH_KEY = "auth-key"
        private const val AUTH_RENEW = "auth-renew"
        private const val AUTH_EXPIRES_AT_KEY = "auth-expires_at"
        private const val AUTH_KEY_TYPE = "auth-key_type"
        private const val USER_CODE_KEY = "profile-code"
        private const val USER_NAME_KEY = "profile-name"
        private const val USER_EMAIL_KEY = "profile-email"
        private const val USER_ADDRESS_KEY = "profile-address"
        private const val USER_PHONE_KEY = "profile-phone"
        private const val USER_AVATAR_KEY = "profile-avatar"
        private const val USER_SEX_KEY = "profile-sex"
        private const val USER_BIRTHDAY_KEY = "profile-birthday"
        private const val USER_CEP_KEY = "profile-cep"
        private const val USER_CITY_KEY = "profile-city"
        private const val USER_UF_KEY = "profile-uf"
        private const val USER_STATUS_KEY = "profile-status"
    }

    init {
        mPreferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
    }

    val isLogged: Boolean
        get() = mPreferences.contains(AUTH_KEY) && !mPreferences.getString(AUTH_KEY, "")!!.isEmpty()

    val auth: Auth
        get() {
            return Auth(
                    accessToken = mPreferences.getString(AUTH_KEY, ""),
                    tokenType = mPreferences.getString(AUTH_KEY_TYPE, ""),
                    expiresIn = mPreferences.getString(AUTH_EXPIRES_AT_KEY, ""),
                    refreshToken = mPreferences.getString(AUTH_RENEW, "")
            )
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
                .putInt(USER_CODE_KEY, user.code)
                .putString(USER_NAME_KEY, user.name)
                .putString(USER_EMAIL_KEY, user.email)
                .putString(USER_ADDRESS_KEY, user.address)
                .putString(USER_PHONE_KEY, user.phone)
                .putString(USER_AVATAR_KEY, user.avatar)
                .putString(USER_SEX_KEY, user.sex)
                .putLong(USER_BIRTHDAY_KEY, user.birthday!!.time)
                .putInt(USER_CEP_KEY, user.cep)
                .putString(USER_CITY_KEY, user.city)
                .putString(USER_UF_KEY, user.uf)
                .putString(USER_STATUS_KEY, user.status)
                .apply()
    }

    fun getUser() : User{
        return User(
                code = mPreferences.getInt(USER_CODE_KEY, 0),
                name = mPreferences.getString(USER_NAME_KEY, ""),
                email = mPreferences.getString(USER_EMAIL_KEY, ""),
                address = mPreferences.getString(USER_ADDRESS_KEY, ""),
                phone = mPreferences.getString(USER_PHONE_KEY, ""),
                avatar = mPreferences.getString(USER_AVATAR_KEY, ""),
                sex = mPreferences.getString(USER_SEX_KEY, ""),
                birthday = Date(mPreferences.getLong(USER_BIRTHDAY_KEY, 0)),
                cep = mPreferences.getInt(USER_CEP_KEY, 0),
                city = mPreferences.getString(USER_CITY_KEY, ""),
                uf = mPreferences.getString(USER_UF_KEY, ""),
                status = mPreferences.getString(USER_STATUS_KEY, "")
        )
    }
}
