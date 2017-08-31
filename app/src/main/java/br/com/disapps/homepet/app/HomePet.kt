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
    var hoteltRepository: HotelRepository? = null

    init {

    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        restApi = RestClient().api

        Fresco.initialize(this)

        preferences = Preferences(instance!!)

        homePetRepository = HomePetRepository(this, preferences as Preferences, cacheDir, restApi as RestApi);
        hoteltRepository = HotelRepository(this, preferences as Preferences, cacheDir, restApi as RestApi);
    }

    companion object {

        var instance: HomePet? = null
            private set
    }
}