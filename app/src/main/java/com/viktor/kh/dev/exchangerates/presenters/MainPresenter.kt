package com.viktor.kh.dev.exchangerates.presenters

import android.content.Context
import android.util.Log
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.data.ExchangeRateRoom
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.repository.ExchangeRateDao
import com.viktor.kh.dev.exchangerates.repository.Repository
import com.viktor.kh.dev.exchangerates.services.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.concurrent.thread


class MainPresenter @Inject constructor() {

    lateinit var tempCurrencyPojo: CurrencyPojo
    lateinit var mainView: MainView
    @Inject
    lateinit var context: Context
    @Inject
    lateinit var networkService: NetworkService
   @Inject
   lateinit var repository: Repository

    var isMainView = true


    fun init(mainView: MainView){
        App.component.inject(this)
        this.mainView = mainView
        repository.initMainPresenter(this)
    }

    fun getCourses(date:String){
        repository.getCourses(date)
    }

    fun setCourses(cur:CurrencyPojo){
        tempCurrencyPojo = cur
        if(isMainView){
            initShortList()
        }

    }

    fun initShortList(){
        Log.d("MyLog", "start initShortList")
        var list = tempCurrencyPojo.exchangeRate.toMutableList()
        Log.d("MyLog", "list size = ${list.size}")
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
        Log.d("MyLog", "start initFullList")
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

    fun errorGetData(){
        mainView.error(context.getString(R.string.error_get_data))

    }








}