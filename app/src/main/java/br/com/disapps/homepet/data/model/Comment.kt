package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by diefferson.santos on 23/08/17.
 */
class Comment(
                @SerializedName("code_hotel") var codeHotel: Int,
                @SerializedName("code_user") var codeUser: Int,
                @SerializedName("name_user") var nameUser: String,
                @SerializedName("avatar_user") var avatarUser: String,
                var comment: String,
                var date : Date
            )
