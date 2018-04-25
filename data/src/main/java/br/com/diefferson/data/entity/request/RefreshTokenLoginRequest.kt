package br.com.diefferson.data.entity.request

import com.google.gson.annotations.SerializedName

/**
* Created by diefferson.santos on 31/08/17.
*/

class RefreshTokenLoginRequest(
        @SerializedName("grant_type")
        var grantType: String = "",
        @SerializedName("refresh_token")
        var refreshToken: String = ""
)


