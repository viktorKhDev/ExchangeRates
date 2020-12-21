package com.viktor.kh.dev.exchangerates.repository

import android.util.Log
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.data.ExchangeRateRoom
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.services.graph.GraphData
import com.viktor.kh.dev.exchangerates.services.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class Repository @Inject constructor() {

    @Inject
    lateinit var dao: ExchangeRateDao
    @Inject
    lateinit var networkService: NetworkService

    private lateinit var mainPresenter: MainPresenter


    fun initMainPresenter(mainPresenter: MainPresenter){
        App.component.inject(this)
        this.mainPresenter = mainPresenter
        networkService.initMainPresenter(mainPresenter)
    }


    fun getCourses(date: String){
        GlobalScope.launch(Dispatchers.IO) {
            val list =  dao.getAll()
            if(list.isNotEmpty()){
               getForDate(list,date)
           }else{
               getFromNetwork(date)
           }
        }
    }

    fun updateDb( tempCurrencyPojo :CurrencyPojo){
        GlobalScope.launch(Dispatchers.IO) {
            for (i in tempCurrencyPojo.exchangeRate) {
              var exRoom = ExchangeRateRoom(
                    null,
                    tempCurrencyPojo.date,
                    i.baseCurrency,
                    i.currency,
                    i.saleRateNB,
                    i.purchaseRateNB,
                    i.saleRate,
                    i.purchaseRate
                )
                dao.insert(
                       exRoom

                    )
            }
        }

    }


     private fun getFromNetwork(date: String){
         GlobalScope.launch(Dispatchers.Main){
           networkService.getAllCourses(date)
       }

   }


     private fun getForDate(list: List<ExchangeRateRoom>,date: String) {
         val exchange = mutableListOf<ExchangeRate>()
            for(i in list){
                if(i.date==date)
                exchange.add(ExchangeRate(
                    i.baseCurrency,
                    i.currency,
                    i.saleRateNB,
                    i.purchaseRateNB,
                    i.saleRate,
                    i.purchaseRate
                ))
            }

         if (exchange.isNotEmpty()){

            GlobalScope.launch(Dispatchers.Main){
                mainPresenter.setCourses(CurrencyPojo(
                    date,
                    "PB",
                    980,
                    "UAH",
                    exchange
                ))
            }

        }else{
              getFromNetwork(date)
          }
      }



    fun getDataForGraph(currencyName: String){
       GlobalScope.launch (Dispatchers.IO){
           GraphData(mainPresenter).getGraph(currencyName)
       }

    }


}