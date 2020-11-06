package com.viktor.kh.dev.exchangerates.presenters

import android.content.Context
import android.util.Log
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.DataForAdapter
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.repository.Repository
import com.viktor.kh.dev.exchangerates.services.network.NetworkService
import java.text.FieldPosition
import javax.inject.Inject



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

        mainView.initShortList(concatLists(newList,null,null),tempCurrencyPojo.date)

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
        mainView.initFullList(concatLists(list,null,null),tempCurrencyPojo.date)
    }

    fun errorGetData(){
        mainView.error(context.getString(R.string.error_get_data))

    }


    fun getDataForGraph(currencyName: String,position: Int) {
        Log.d("MyLog", "currencyName = ${currencyName}")
         repository.getDataForGraph(currencyName)
    }


    fun setGraph(list:LineGraphSeries<DataPoint>,currencyName: String) {

        mainView.openGraph(concatLists(tempCurrencyPojo.exchangeRate,list,currencyName))
   }


    private fun concatLists(exchangeRates: List<ExchangeRate>,graphList:LineGraphSeries<DataPoint>?,currencyName: String?):List<DataForAdapter> {
        var dataList = mutableListOf<DataForAdapter>()
        if (graphList==null||currencyName==null){
            for (i in exchangeRates){
                dataList.add(DataForAdapter(i,null))
            }
        }else{
            for (i in exchangeRates){
                if(i.currency!=currencyName){
                    dataList.add(DataForAdapter(i,null))
                }else{
                    dataList.add(DataForAdapter(i,graphList))
                }

            }
        }
        return dataList
    }





}