package br.com.diefferson.data.entity

import com.google.gson.annotations.SerializedName
import java.util.*

/**
* Created by diefferson.santos on 23/08/17.
*/
class Rating(
            @SerializedName("code_hotel") var codeHotel: Int,
            @SerializedName("code_user") var codeUser: Int,
            var rating: Float,
            var date: Date
        )


