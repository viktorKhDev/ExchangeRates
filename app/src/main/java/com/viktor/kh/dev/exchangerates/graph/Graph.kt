package com.viktor.kh.dev.exchangerates.graph

import com.viktor.kh.dev.exchangerates.data.ExchangeRateRoom
import com.viktor.kh.dev.exchangerates.data.GraphData
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.repository.ExchangeRateDao
import com.viktor.kh.dev.exchangerates.repository.Repository
import com.viktor.kh.dev.exchangerates.utils.DATE_FORMAT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class Graph(_presenter: MainPresenter) {

    @Inject
    lateinit var  repository: Repository

    @Inject
    lateinit var dao: ExchangeRateDao

    lateinit var presenter: MainPresenter

    init {
        App.component.inject(this)
        presenter = _presenter
    }



    public suspend fun getGraph(currencyName: String) {
        var list = dao.getForCurrency(currencyName)
        var graphList = getList(list)
        GlobalScope.launch(Dispatchers.Main) {
            presenter.setGraph(graphList, currencyName)

        }
    }


    private fun getList(list: List<ExchangeRateRoom>):List<GraphData>{
        var listGraph = mutableListOf<GraphData>()
        val format = SimpleDateFormat(DATE_FORMAT,Locale.ENGLISH)
        for (i in list){
            var date: Date = format.parse(i.date)
            val value  = i.saleRateNB
            if (date!=null&&value!=null){
                listGraph.add(GraphData(date.time,value))
            }
        }
        listGraph.sortBy { i -> i.date }



        return listGraph
    }


}