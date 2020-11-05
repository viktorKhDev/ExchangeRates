package com.viktor.kh.dev.exchangerates.presenters


import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.viktor.kh.dev.exchangerates.data.ExchangeRate

interface MainView {

    fun initShortList(list: List<ExchangeRate>,date:String)
    fun initFullList(list: List<ExchangeRate>,date: String)
    fun error(text: String)
    fun openGraph(list: LineGraphSeries<DataPoint>)

}