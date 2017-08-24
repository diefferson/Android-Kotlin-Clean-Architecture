package br.com.disapps.homepet.data.cache

import java.io.File

import br.com.disapps.homepet.data.prefs.Preferences
import br.com.disapps.homepet.data.ws.RestApi
import io.rx_cache2.internal.RxCache
import io.victoralbertos.jolyglot.GsonSpeaker

/**
 * Created by diefferson.santos on 23/08/17.
 */

abstract class BaseRepository(protected val preferences: Preferences, cacheDir: File, protected val restApi: RestApi) {
    protected val providers: Providers

    init {
        this.providers = RxCache.Builder()
                .persistence(cacheDir, GsonSpeaker())
                .using(Providers::class.java)
    }
}
