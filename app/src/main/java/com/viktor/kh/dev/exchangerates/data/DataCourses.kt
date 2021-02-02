package com.viktor.kh.dev.exchangerates.data
import com.viktor.kh.dev.exchangerates.graph.Graph

data class DataCourses (
    var date: String,
    var lastNameClicked:String?,
    var graph: Graph?,
    var exchangeRates: List<ExchangeRate>
)