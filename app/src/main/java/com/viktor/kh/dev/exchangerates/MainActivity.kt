package com.viktor.kh.dev.exchangerates

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.viktor.kh.dev.exchangerates.presenters.MainView

class MainActivity  :  MainView, AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getCourses() {

        TODO("Not yet implemented")
    }
}