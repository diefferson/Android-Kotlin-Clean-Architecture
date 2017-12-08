package br.com.disapps.homepet.data.model

import android.net.Uri
import com.google.gson.annotations.SerializedName
import java.util.*

/**
* Created by diefferson.santos on 24/08/17.
*/
class User (
        var code: Int = 0,
        var name: String = "",
        var email: String = "",
        var address: String = "",
        var phone: String = "",
        var avatar: String = "",
        var sex: String = "",
        var birthday: Date? = null,
        var cep: Int = 0,
        var city: String = "",
        var uf: String = "",
        var status: String = "",
        var imageUri:Uri? = null
    )