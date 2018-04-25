package br.com.diefferson.data.dataSource.local

import br.com.diefferson.data.dataSource.HotelsDataSource
import br.com.diefferson.data.entity.response.ApiListResponse

class LocalHotelsDataSource : HotelsDataSource{

    override suspend fun getHotels(sort: String, sense: String): ApiListResponse.ListHotelResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}