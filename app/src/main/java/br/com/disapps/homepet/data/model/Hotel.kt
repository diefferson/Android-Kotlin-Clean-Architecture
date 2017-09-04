package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by diefferson.santos on 23/08/17.
 */

class Hotel : Serializable{

    var code: Int = 0
    var name: String? = null
    var email: String? = null
    var address: String? = null
    var price: Float? = null

    @SerializedName("contact_email") var contactEmail: String? = null
    @SerializedName("contact_phone") var contactPhone: String? = null

    var coordenates: Coordenate? = null
    var cep: String? = null
    var city: String? = null
    var uf: String? = null
    var status: String? = null
    var description: String? = null

    @SerializedName("cover_image") var coverImage: String? = null

    var rating:Float = 0F

    @SerializedName("ratings_number") var ratingsNumber: Int = 0
    @SerializedName("comments_number") var commentsNumber: Int = 0

    var images: List<String>? = null
    var services: List<Service>? = null
}
