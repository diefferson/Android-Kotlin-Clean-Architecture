package br.com.disapps.homepet.data.ws.request

import com.google.gson.annotations.SerializedName

/**
* Created by diefferson.santos on 31/08/17.
*/
class PasswordLoginRequest (
    @SerializedName("grant_type")
    var grantType: String = "",
    var username: String = "",
    var password: String = ""
)
