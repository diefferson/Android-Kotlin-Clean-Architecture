package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by diefferson.santos on 23/08/17.
 */
class Auth (var key: String,var renew: String, @SerializedName("expires_at") var expiresAt: String)