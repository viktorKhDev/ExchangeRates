package com.viktor.kh.dev.exchangerates.presenters

import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.ExchangeRate

interface MainView {

    fun initShortList(list: List<ExchangeRate>,date:String)
    fun initFullList(list: List<ExchangeRate>,date: String)

}