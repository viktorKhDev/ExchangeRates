package com.viktor.kh.dev.exchangerates.data

import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.viktor.kh.dev.exchangerates.data.ExchangeRate

data class DataForAdapter(
    val exchangeRate: ExchangeRate,
    val initGraph: LineGraphSeries<DataPoint>?
)