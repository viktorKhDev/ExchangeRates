package com.viktor.kh.dev.exchangerates.repository

import android.util.Log
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.data.ExchangeRateRoom
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import com.viktor.kh.dev.exchangerates.services.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class Repository @Inject constructor() {

    @Inject
    lateinit var dao: ExchangeRateDao
    @Inject
    lateinit var networkService: NetworkService

    lateinit var mainPresenter: MainPresenter


    fun initMainPresenter(mainPresenter: MainPresenter){
        App.component.inject(this)
        this.mainPresenter = mainPresenter
        networkService.initMainPresenter(mainPresenter)
    }


    fun getCourses(date: String){
        Log.d("MyLog", "start getCourses")
        GlobalScope.launch(Dispatchers.IO) {
            val list =  dao.getAll()

           if(list.isNotEmpty()){
               Log.d("MyLog", list.size.toString())
               getForDate(list,date)
           }else{
               getFromNetwork(date)
           }
        }
    }

    fun updateDb( tempCurrencyPojo :CurrencyPojo){
        Log.d("MyLog", "start updateDb")
        Log.d("MyLog", "list for update db size - ${tempCurrencyPojo.exchangeRate.size}-----------------------------------------")
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


    fun getFromNetwork(date: String){
        Log.d("MyLog", "start getFromNetwork")
       GlobalScope.launch(Dispatchers.Main){
           networkService.getAllCourses(date)
       }

   }


      fun getForDate(list: List<ExchangeRateRoom>,date: String) {
          Log.d("MyLog", "start getForDate")
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

          Log.d("MyLog", exchange.size.toString())

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


}