package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by diefferson.santos on 24/08/17.
 */
class User {
    var code: Int? = null
    var name: String? = null
    var email: String? = null
    var address: String? = null
    var phone: String? = null
    var avatar: String? = null
    var sex: String? = null
    var birthday: Date? = null
    var cep: Int? = null
    var city: String? = null
    var uf: String?= null
    var status: String? = null
}