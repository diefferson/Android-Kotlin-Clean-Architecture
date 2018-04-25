package br.com.diefferson.data.dataSource.cloud

import br.com.diefferson.data.api.RestApi
import br.com.diefferson.data.dataSource.HotelsDataSource
import br.com.diefferson.data.entity.response.ApiListResponse

class CloudHotelsDataSource(private val restApi: RestApi) : HotelsDataSource{

    override suspend fun getHotels(sort: String, sense: String): ApiListResponse.ListHotelResponse {
        return restApi.getHotels(sort, sense).await()
    }
}