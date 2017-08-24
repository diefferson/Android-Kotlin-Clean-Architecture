package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by diefferson.santos on 23/08/17.
 */
class Comentario(@SerializedName("codigo_hotel") var codigoHotel: Int, @SerializedName("codigo_usuario") var codigoUsuario: Int, var comentario: String, var data : Date)

