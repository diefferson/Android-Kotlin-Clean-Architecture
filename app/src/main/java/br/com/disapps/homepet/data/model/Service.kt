package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by diefferson.santos on 24/08/17.
 */
class Service (
        @SerializedName("code_service") var codeService:Int,
        var name:String
): Serializable
