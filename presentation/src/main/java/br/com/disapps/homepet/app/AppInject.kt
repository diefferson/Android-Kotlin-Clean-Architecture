package br.com.disapps.homepet.app

import android.content.Context
import br.com.diefferson.data.api.RestApi
import br.com.diefferson.data.executor.JobContextExecutor
import br.com.diefferson.domain.executor.ContextExecutor
import br.com.diefferson.domain.executor.PostExecutionContext
import br.com.diefferson.data.api.RestClient
import br.com.diefferson.data.dataSource.factory.HotelsDataSourceFactory
import br.com.diefferson.data.repository.HotelsDataRepository
import br.com.diefferson.data.storage.preferences.Preferences
import br.com.diefferson.domain.interactor.hotel.GetHotels
import br.com.diefferson.domain.repository.HotelsRepository
import br.com.disapps.homepet.executor.UIContext
import br.com.disapps.homepet.ui.common.BaseViewModel
import br.com.disapps.homepet.ui.hotels.HotelsViewModel
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext

object AppInject {

    private const val APP_CONTEXT = "applicationContext"

    fun modules(): List<Module> = listOf(
            applicationModule,
            viewModelModule,
            repositoriesModule,
            useCaseModule,
            dataSourceFactoryModule
    )

    private val applicationModule: Module = applicationContext {
        bean(APP_CONTEXT) { HomePet.instance!! as Context }
        bean { Preferences(context = get(APP_CONTEXT)) }
        bean { RestClient(preferences = get()).api }
        bean { UIContext() as PostExecutionContext }
        bean { JobContextExecutor() as ContextExecutor }
    }

    private val viewModelModule = applicationContext {
        viewModel { BaseViewModel() }
        viewModel { HotelsViewModel(getHotelsUseCase = get())  }
    }

    private val useCaseModule: Module = applicationContext {
        factory { GetHotels(hotelsRepository = get(), contextExecutor = get(), postExecutionContext = get()) }
    }

    private val repositoriesModule: Module = applicationContext {
        bean { HotelsDataRepository(hotelsDataSourceFactory = get()) as HotelsRepository}
    }

    private val dataSourceFactoryModule: Module = applicationContext {
        bean { HotelsDataSourceFactory(restApi = get()) }
    }
}
