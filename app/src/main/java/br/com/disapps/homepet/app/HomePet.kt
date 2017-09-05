package br.com.disapps.homepet.app

import android.app.Application

import com.facebook.drawee.backends.pipeline.Fresco

import br.com.disapps.homepet.data.cache.HomePetRepository
import br.com.disapps.homepet.data.cache.HotelRepository
import br.com.disapps.homepet.data.prefs.Preferences
import br.com.disapps.homepet.data.ws.RestApi
import br.com.disapps.homepet.data.ws.RestClient

/**
 * Created by diefferson.santos on 23/08/17.
 */
class HomePet : Application() {

    var restApi: RestApi? = null
        private set

    var preferences: Preferences? = null
        private set

    var homePetRepository: HomePetRepository? = null
        private set
        get(){
            if(homePetRepository == null) {
                homePetRepository = HomePetRepository(this, preferences as Preferences, cacheDir, restApi as RestApi)
            }
            return homePetRepository;
        }

    var hotelRepository: HotelRepository? = null
        private set
        get(){
            if(hotelRepository == null) {
                hotelRepository = HotelRepository(this, preferences as Preferences, cacheDir, restApi as RestApi)
            }
            return hotelRepository;
        }

    init {

    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        restApi = RestClient().api

        Fresco.initialize(this)

        preferences = Preferences(instance!!)

    }

    companion object {
        var instance: HomePet? = null
            private set
    }
}