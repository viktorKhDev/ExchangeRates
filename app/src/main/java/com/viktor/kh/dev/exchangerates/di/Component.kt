package com.viktor.kh.dev.exchangerates.di

import com.viktor.kh.dev.exchangerates.MainActivity
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.services.network.NetworkService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,MainPresenterModule::class,NetworkModule::class])
interface Component {

    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
    fun inject(networkService: NetworkService)

}