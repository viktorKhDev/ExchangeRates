package com.viktor.kh.dev.exchangerates.data
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

data class DataCourses (
    var date: String,
    var lastNameClicked:String?,
    var mapForGraph: MutableMap<String,LineGraphSeries<DataPoint>>?,
    var exchangeRates: List<ExchangeRate>
)