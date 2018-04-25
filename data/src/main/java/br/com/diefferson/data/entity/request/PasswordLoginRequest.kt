package br.com.diefferson.data.entity.request

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
