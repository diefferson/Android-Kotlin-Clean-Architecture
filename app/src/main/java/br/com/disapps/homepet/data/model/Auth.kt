package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by diefferson.santos on 23/08/17.
 */
class Auth (
        @SerializedName("access_token") var accessToken: String,
        @SerializedName("token_type") var tokenType: String,
        @SerializedName("expires_in") var expiresIn: String,
        @SerializedName("refresh_token") var refreshToken: String
)