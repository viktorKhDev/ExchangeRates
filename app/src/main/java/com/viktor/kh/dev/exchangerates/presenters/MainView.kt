package com.viktor.kh.dev.exchangerates.presenters

import com.viktor.kh.dev.exchangerates.data.CurrencyPojo

interface MainView {

    fun setCourses(cur: CurrencyPojo)

}