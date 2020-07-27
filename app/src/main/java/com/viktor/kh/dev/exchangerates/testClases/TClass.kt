package com.viktor.kh.dev.exchangerates.testClases

import javax.inject.Inject

class TClass @Inject constructor() {

    private val name = "Name"



    fun getName(s:String):String{
        return "$s$name"
    }
}