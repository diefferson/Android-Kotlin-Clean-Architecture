package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by diefferson.santos on 24/08/17.
 */
class Service(
                @SerializedName("code_service") var codeService:Int,
                var name:String
            )
