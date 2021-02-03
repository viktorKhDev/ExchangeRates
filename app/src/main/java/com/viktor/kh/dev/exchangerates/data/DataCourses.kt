package com.viktor.kh.dev.exchangerates.data
import com.viktor.kh.dev.exchangerates.graph.Graph

data class DataCourses (
    var date: String,
    var lastNameClicked:String?,
    var graphData:Map <String,List<GraphData>>?,
    var exchangeRates: List<ExchangeRate>
)