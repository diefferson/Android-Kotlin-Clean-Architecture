package br.com.diefferson.data.entity.request

import com.google.gson.annotations.SerializedName

/**
 * Created by diefferson on 09/10/17.
 **/
class IncludeCommentRequest (@SerializedName("code_hotel") var codeHotel: Int, var comment:String)
