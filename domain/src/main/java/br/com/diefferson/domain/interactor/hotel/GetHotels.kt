package br.com.diefferson.domain.interactor.hotel

import br.com.diefferson.domain.executor.ContextExecutor
import br.com.diefferson.domain.executor.PostExecutionContext
import br.com.diefferson.domain.interactor.base.UseCase
import br.com.diefferson.domain.model.Hotel
import br.com.diefferson.domain.repository.HotelsRepository

class GetHotels(private val hotelsRepository: HotelsRepository,
                val contextExecutor: ContextExecutor, val postExecutionContext: PostExecutionContext) : UseCase<List<Hotel>, GetHotels.Params>(contextExecutor,postExecutionContext){

    override suspend fun buildUseCaseObservable(params: Params): List<Hotel> {
        return hotelsRepository.getHotels(params.sort, params.sense)
    }

    class Params(val sort: String, val sense : String)
}