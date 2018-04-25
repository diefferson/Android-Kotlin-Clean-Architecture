package br.com.diefferson.domain.repository

import br.com.diefferson.domain.model.Hotel

interface HotelsRepository{

    suspend fun getHotels(sort: String, sense: String) : List<Hotel>
}