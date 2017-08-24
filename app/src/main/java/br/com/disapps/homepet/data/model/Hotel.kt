package br.com.disapps.homepet.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by diefferson.santos on 23/08/17.
 */

class Hotel {

    var codigo: Int = 0
    var nome: String? = null
    var email: String? = null
    var senha: String? = null
    var endereco: String? = null

    @SerializedName("email_contato")
    var emailContato: String? = null

    @SerializedName("telefone_contato")
    var telefoneContato: String? = null

    var coordenadas: Coordenadas? = null
    var cep: String? = null
    var cidade: String? = null
    var uf: String? = null
    var status: String? = null
    var descricao: String? = null

    @SerializedName("url_imagem_capa")
    var urlImagemCapa: String? = null

    var imagens: List<String>? = null
}
