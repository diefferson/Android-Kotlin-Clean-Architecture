package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by diefferson.santos on 24/08/17.
 */
class Usuario{
    var codigo: Int? = null
    var nome: String? = null
    var email: String? = null
    var senha: String? = null
    var endereco: String? = null
    var telefone: String? = null

    @SerializedName("url_imagem_perfil")
    var urlImagemPerfil: String? = null
    var sexo: Char? = null

    @SerializedName("data_nascimento")
    var dataNascimento: Date? = null

    var cep: Int? = null
    var cidade: String? = null
    var uf: String?= null
    var status: String? = null
}