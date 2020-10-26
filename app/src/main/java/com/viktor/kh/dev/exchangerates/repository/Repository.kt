package com.viktor.kh.dev.exchangerates.repository

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

class Repository {

    @Inject
    lateinit var dao: ExchangeRateDao
    @Inject
    lateinit var networkService: NetworkService

    lateinit var mainPresenter: MainPresenter


    @Inject constructor(){
        App.component.inject(this)
    }

    fun initMainPresenter(mainPresenter: MainPresenter){
        this.mainPresenter = mainPresenter
        networkService.initMainPresenter(mainPresenter)
    }

    fun updateDb( tempCurrencyPojo :CurrencyPojo){
        GlobalScope.launch(Dispatchers.IO) {
            if(!isForDate(tempCurrencyPojo.date))
                for (i in tempCurrencyPojo.exchangeRate) {
                    dao.insert(
                        ExchangeRateRoom(
                            0,
                            tempCurrencyPojo.date,
                            i.baseCurrency,
                            i.currency,
                            i.saleRateNB,
                            i.purchaseRateNB,
                            i.saleRate,
                            i.purchaseRate
                        )
                    )


                }
        }

    }

    fun isForDate(date:String):Boolean{
        return dao.getForDate(date)!=null
    }


  suspend fun getForDate(date:String) {

      networkService.getAllCourses(date)

       /* if (!isForDate(date)){
            networkService.getAllCourses(date)
        }else{
            val exchange = mutableListOf<ExchangeRate>()
            for(i in dao.getForDate(date)){
                exchange.add(ExchangeRate(
                    i.baseCurrency,
                    i.currency,
                    i.saleRateNB,
                    i.purchaseRateNB,
                    i.saleRate,
                    i.purchaseRate
                ))
            }


            GlobalScope.launch(Dispatchers.Main){
                mainPresenter.setCourses(CurrencyPojo(
                    date,
                    "PB",
                    980,
                    "UAH",
                    exchange
                ))
            }

        }*/



    }


}