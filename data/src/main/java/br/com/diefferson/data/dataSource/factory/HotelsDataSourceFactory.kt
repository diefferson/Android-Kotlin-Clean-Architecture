package br.com.diefferson.data.dataSource.factory

import br.com.diefferson.data.api.RestApi
import br.com.diefferson.data.dataSource.DataSource
import br.com.diefferson.data.dataSource.HotelsDataSource
import br.com.diefferson.data.dataSource.cloud.CloudHotelsDataSource
import br.com.diefferson.data.dataSource.local.LocalHotelsDataSource

class HotelsDataSourceFactory(private val restApi: RestApi) : DataSourceFactory{
    override fun create(useCloud: Boolean): HotelsDataSource {
        return if(useCloud){
            CloudHotelsDataSource(restApi)
        }else{
            LocalHotelsDataSource()
        }
    }
}