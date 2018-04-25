package br.com.disapps.homepet.ui.hotels

import android.arch.lifecycle.MutableLiveData
import br.com.diefferson.domain.interactor.hotel.GetHotels
import br.com.diefferson.domain.model.Hotel
import br.com.disapps.homepet.ui.common.BaseViewModel

class HotelsViewModel(private val getHotelsUseCase: GetHotels) : BaseViewModel(){

    val hotels = MutableLiveData<List<Hotel>>()
    var sense  = ""
    var sort = ""

    fun getHotels(){
        getHotelsUseCase.execute(GetHotels.Params(sort, sense),
                onError = {},
                onSuccess ={
                    hotels.value = it
                }
        )
    }
}