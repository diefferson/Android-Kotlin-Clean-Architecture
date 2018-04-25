package br.com.diefferson.domain.model

/**
* Created by diefferson.santos on 23/08/17.
*/

class Hotel (
        var code: Int = 0,
        var name: String = "",
        var email: String = "",
        var address: String = "",
        var price: Float = 0F,
        var contactEmail: String = "",
        var contactPhone: String = "",
        var coordenates: Coordinate? = null,
        var cep: String = "",
        var city: String = "",
        var uf: String = "",
        var status: String = "",
        var description: String = "",
        var coverImage: String = "",
        var rating:Float = 0F,
        var ratingsNumber: Int = 0,
        var commentsNumber: Int = 0,
        var images: List<String>? = null,
        var services: List<Service>? = null
        ){

    fun getCompleteAddress():String = "$address - $city $uf"
}
