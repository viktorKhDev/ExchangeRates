package com.viktor.kh.dev.exchangerates.di

import android.app.Application

class App: Application() {

    companion object {
        lateinit var component: Component
    }


    override fun onCreate() {
        super.onCreate()
       initDagger()
    }


   fun initDagger(){
    component = DaggerComponent.builder()
        .appModule(AppModule(this))
        .build()
    }
}