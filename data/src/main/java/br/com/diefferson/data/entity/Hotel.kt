package br.com.diefferson.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
* Created by diefferson.santos on 23/08/17.
*/

class Hotel (
        var code: Int = 0,
        var name: String = "",
        var email: String = "",
        var address: String = "",
        var price: Float = 0F,
        @SerializedName("contact_email") var contactEmail: String = "",
        @SerializedName("contact_phone") var contactPhone: String = "",
        var coordenates: Coordinate? = null,
        var cep: String = "",
        var city: String = "",
        var uf: String = "",
        var status: String = "",
        var description: String = "",
        @SerializedName("cover_image") var coverImage: String = "",
        var rating:Float = 0F,
        @SerializedName("ratings_number") var ratingsNumber: Int = 0,
        @SerializedName("comments_number") var commentsNumber: Int = 0,
        var images: List<String>? = null,
        var services: List<Service>? = null
        ): Serializable{

    fun getCompleteAddress():String = "$address - $city $uf"
}
