package com.viktor.kh.dev.exchangerates.di

import com.viktor.kh.dev.exchangerates.MainActivity
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,MainPresenterModule::class])
interface Component {

    fun inject(mainPresenter: MainPresenter)
    fun inject(mainActivity: MainActivity)
}