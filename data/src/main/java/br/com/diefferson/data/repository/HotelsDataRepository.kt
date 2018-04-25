package br.com.diefferson.data.repository

import br.com.diefferson.data.dataSource.factory.HotelsDataSourceFactory
import br.com.diefferson.data.entity.mapper.toHotelBO
import br.com.diefferson.domain.model.Hotel
import br.com.diefferson.domain.repository.HotelsRepository

class HotelsDataRepository(private val hotelsDataSourceFactory: HotelsDataSourceFactory) : HotelsRepository{

    override suspend fun getHotels(sort: String, sense: String): List<Hotel> {
        return hotelsDataSourceFactory
                .create(true)
                .getHotels(sort, sense)
                .content
                .toHotelBO()

    }
}