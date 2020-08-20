package com.viktor.kh.dev.exchangerates.presenters





import android.content.Context
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.services.network.NetworkService

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

    fun getCourses(){
        networkService.getAllCourses()
    }

    fun setCourses(list: ArrayList<CurrencyPojo>){
        mainView.setCourses(list)
    }










}