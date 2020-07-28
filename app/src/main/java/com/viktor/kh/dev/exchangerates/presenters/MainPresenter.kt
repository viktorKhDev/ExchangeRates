package com.viktor.kh.dev.exchangerates.presenters





import android.content.Context
import com.viktor.kh.dev.exchangerates.di.App

import javax.inject.Inject


class MainPresenter @Inject constructor() {

    lateinit var mainView: MainView
    @Inject
    lateinit var context: Context


    fun init(mainView: MainView){
        App.component.inject(this)
        this.mainView = mainView


    }







}