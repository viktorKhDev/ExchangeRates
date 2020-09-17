package com.viktor.kh.dev.exchangerates.presenters

import android.content.Context
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.services.network.NetworkService
import java.lang.StringBuilder
import java.math.BigDecimal
import javax.inject.Inject


class MainPresenter @Inject constructor() {

    lateinit var mainView: MainView
    @Inject
    lateinit var context: Context
    @Inject
    lateinit var networkService: NetworkService


    fun init(mainView: MainView){
        App.component.inject(this)
        this.mainView = mainView
        networkService.initMainPresenter(this)
    }

    fun getCourses(date:String){
        networkService.getAllCourses(date)
    }

    fun setCourses(cur:CurrencyPojo){

        mainView.setCourses(cur)
    }









}