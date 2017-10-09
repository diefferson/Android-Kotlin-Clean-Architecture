package br.com.disapps.homepet.data.ws.request

import com.google.gson.annotations.SerializedName

/**
 * Created by diefferson on 09/10/17.
 **/
class IncludeCommentRequest (@SerializedName("code_hotel") var codeHotel: Int, var comment:String)
