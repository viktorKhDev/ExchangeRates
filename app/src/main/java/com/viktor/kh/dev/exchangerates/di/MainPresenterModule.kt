package com.viktor.kh.dev.exchangerates.di

import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import dagger.Module
import dagger.Provides



@Module
class MainPresenterModule {

    @Provides
    fun provideMainPresenter():MainPresenter{
        return MainPresenter()
    }

}