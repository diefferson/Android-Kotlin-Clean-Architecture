package br.com.diefferson.data.dataSource.factory

import br.com.diefferson.data.dataSource.DataSource

/**
 * Created by dnso on 15/03/2018.
 */
interface DataSourceFactory {

    fun create(useCloud : Boolean = false) : DataSource
}