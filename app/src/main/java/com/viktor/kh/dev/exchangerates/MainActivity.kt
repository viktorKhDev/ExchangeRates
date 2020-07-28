package com.viktor.kh.dev.exchangerates

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.presenters.MainView
import javax.inject.Inject

class MainActivity  :  MainView, AppCompatActivity() {

    @Inject
    lateinit var mainPresenter:MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.component.inject(this)
        mainPresenter.init(this)
    }







    override fun getCourses(list: ArrayList<CurrencyPojo>) {

    }





}