package br.com.diefferson.data.dataSource

import br.com.diefferson.data.entity.response.ApiListResponse

interface HotelsDataSource : DataSource{

    suspend fun getHotels(sort: String, sense: String): ApiListResponse.ListHotelResponse
}