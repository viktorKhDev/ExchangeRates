package com.viktor.kh.dev.exchangerates.presenters

import android.content.Context
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.data.ExchangeRateRoom
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.repository.ExchangeRateDao
import com.viktor.kh.dev.exchangerates.services.network.NetworkService
import javax.inject.Inject


class MainPresenter @Inject constructor() {

    lateinit var tempCurrencyPojo: CurrencyPojo
    lateinit var mainView: MainView
    @Inject
    lateinit var context: Context
    @Inject
    lateinit var networkService: NetworkService
    @Inject
    lateinit var dao: ExchangeRateDao


    fun init(mainView: MainView){
        App.component.inject(this)
        this.mainView = mainView
        networkService.initMainPresenter(this)
    }

    fun getCourses(date:String){
        networkService.getAllCourses(date)
    }

    fun setCourses(cur:CurrencyPojo){


        tempCurrencyPojo = cur



        initShortList()
    }

    fun initShortList(){
        var list = tempCurrencyPojo.exchangeRate.toMutableList()
        var newList =  mutableListOf<ExchangeRate>()

        for (i in 0..list.size-1){
            if(list[i].currency==("USD")
                ||list[i].currency==("EUR")
                ||list[i].currency==("GBP")
                ||list[i].currency==("PLZ")
                ||list[i].currency==("RUB")){
                newList.add(list[i])

            }
        }

        mainView.initShortList(newList,tempCurrencyPojo.date)

    }

    fun initFullList(){
        var list = tempCurrencyPojo.exchangeRate.toMutableList()
        list.removeAt(0)
        var num: Int = 0
        for (i in 0..list.size-1){
            if(list[i].currency==("UAH")){
                num = i
            }
        }
        list.removeAt(num)
        mainView.initFullList(list,tempCurrencyPojo.date)
    }





 /* suspend fun updateDb(){
      for (i in tempCurrencyPojo.exchangeRate){
          dao.insert(ExchangeRateRoom(
              0,
              tempCurrencyPojo.date,
              i.baseCurrency,
              i.currency,
              i.saleRateNB,
              i.purchaseRateNB,
              i.saleRate,
              i.purchaseRate
          ))
}
*/




}