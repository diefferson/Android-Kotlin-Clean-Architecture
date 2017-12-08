package br.com.disapps.homepet.app

import android.app.Application

import br.com.disapps.homepet.data.cache.HotelRepository
import br.com.disapps.homepet.data.prefs.Preferences
import br.com.disapps.homepet.data.ws.RestApi
import br.com.disapps.homepet.data.ws.RestClient

/**
* Created by diefferson.santos on 23/08/17.
*/
class HomePet : Application() {

    val restApi: RestApi by lazy {
        RestClient().api
    }

    val preferences: Preferences by lazy {
        Preferences(this)
    }

    val hotelRepository: HotelRepository by lazy {
        HotelRepository( preferences , cacheDir, restApi)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: HomePet? = null
            private set
    }
}