package com.viktor.kh.dev.exchangerates.repository

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings.Global.getString
import com.viktor.kh.dev.exchangerates.R
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
    @Inject
    lateinit var context: Context

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



    fun getFirstPreferencesBoolen(prefName:String): Boolean{
        val preferences = context.getSharedPreferences(context.getString(R.string.pref_first_launch), Context.MODE_PRIVATE)
        return preferences.getBoolean(prefName,false)
    }

    fun setFirstPreferencesBoolen(prefName:String){
        val preferences = context.getSharedPreferences(context.getString(R.string.pref_first_launch), Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putBoolean(context.getString(R.string.preference_first_graph),true)
        editor.commit()
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