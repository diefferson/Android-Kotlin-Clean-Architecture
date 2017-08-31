package br.com.disapps.homepet.data.ws.request

import com.google.gson.annotations.SerializedName

/**
 * Created by diefferson.santos on 31/08/17.
 */

class RefreshTokenLoginRequest{
        @SerializedName("grant_type")
        var grantType: String? = null
        @SerializedName("refresh_token")
        var refreshToken: String? = null
}
