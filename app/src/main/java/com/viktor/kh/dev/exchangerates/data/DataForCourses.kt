package com.viktor.kh.dev.exchangerates.data
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

data class DataForCourses (
    val date: String,
    val mapForGraph: MutableMap<String,LineGraphSeries<DataPoint>>?,
    val exchangeRates: List<ExchangeRate>
)