package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by diefferson.santos on 24/08/17.
 */
class Servico(@SerializedName("codigo_servico") var codigoServico:Int, var nome:String)
