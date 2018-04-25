package br.com.disapps.homepet.app

import android.app.Application
import android.support.v7.app.AppCompatDelegate
import org.koin.android.ext.android.startKoin

/**
* Created by diefferson.santos on 23/08/17.
*/
class HomePet : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)

        startKoin(this, AppInject.modules())
    }

    companion object {
        var instance: HomePet? = null
            private set
    }
}