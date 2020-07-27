package com.viktor.kh.dev.exchangerates.presenters





import android.content.Context
import android.util.Log
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.di.DaggerComponent

import com.viktor.kh.dev.exchangerates.testClases.TClass
import javax.inject.Inject


class MainPresenter @Inject constructor() {

    lateinit var mainView: MainView
    @Inject
    lateinit var context: Context


    fun init(mainView: MainView){
        App.component.inject(this)
        this.mainView = mainView
        Log.d("MyLog","mainView = ${mainView.getCourses()} context = ${context.packageName}")
    }







}