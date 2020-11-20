package com.viktor.kh.dev.exchangerates.presenters

import android.content.Context
import android.util.Log
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.DataForCourses
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.repository.Repository
import com.viktor.kh.dev.exchangerates.services.network.NetworkService
import java.lang.Exception
import javax.inject.Inject



class MainPresenter @Inject constructor() {

    var isMainView = true

    lateinit var tempCurrencyPojo: CurrencyPojo
    lateinit var mainView: MainView
    @Inject
    lateinit var context: Context
    @Inject
    lateinit var networkService: NetworkService
   @Inject
    lateinit var repository: Repository


    //checking the size of the list on the screen
    var isShortList = true


     lateinit var dataForFragment: DataForCourses







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
            initList()
        }

    }




    fun initList(){

            if (isShortList){
                initShortList()
            }else{
                initFullList()
            }
       try {
           if (dataForFragment.exchangeRates.isNotEmpty()){
               mainView.initList(dataForFragment)
           }else{
               errorGetData()
           }
       }catch (e:Exception){
           errorGetData()
       }


    }

    private fun initShortList(){

        try {
            Log.d("MyLog", "start initShortList")
            var list = tempCurrencyPojo.exchangeRate.toMutableList()
            if (list.size>0){

                Log.d("MyLog", "list size = ${list.size}")
                var newList =  mutableListOf<ExchangeRate>()

                for (i in 0 until list.size){
                    if(list[i].currency==("USD")
                        ||list[i].currency==("EUR")
                        ||list[i].currency==("GBP")
                        ||list[i].currency==("PLZ")
                        ||list[i].currency==("RUB")){
                        newList.add(list[i])

                    }
                }


                Log.d("MyLog", "list size after = ${newList.size}")

                if (this::dataForFragment.isInitialized){
                    val data = dataForFragment

                    dataForFragment = DataForCourses(data.date,data.mapForGraph,newList)
                }
                else{
                    dataForFragment = DataForCourses(tempCurrencyPojo.date,null,newList)
                }

                isShortList = true
            }
        }catch (e:Exception){
            errorGetData()
        }


    }





    private fun initFullList(){
        try {
            Log.d("MyLog", "start initFullList")
            var list = tempCurrencyPojo.exchangeRate.toMutableList()
            if (list.size>0){
                list.removeAt(0)

                var i  = 0

                while (i<list.size){
                    if(list[i].currency==("UAH")){
                        list.removeAt(i)
                        continue
                    }
                    if (list[i].currency==null){
                        list.removeAt(i)
                        continue
                    }
                    i += 1
                }


                Log.d("MyLog", "full list size = ${list.size}")


                dataForFragment = if (this::dataForFragment.isInitialized){
                    val data = dataForFragment

                    DataForCourses(data.date,data.mapForGraph,list)
                } else{
                    DataForCourses(tempCurrencyPojo.date,null,list)
                }


                isShortList = false

            }
        }catch (e:Exception){
            errorGetData()
        }




    }

    fun setGraph(list:LineGraphSeries<DataPoint>,currencyName: String) {
        val map = mutableMapOf<String,LineGraphSeries<DataPoint>>()
        if (dataForFragment.mapForGraph!=null){
            for (i in dataForFragment.mapForGraph!!){
                map.put(i.key,i.value)
            }
        }

        map.put(currencyName,list)

        val data = dataForFragment

        dataForFragment = DataForCourses(data.date,map,data.exchangeRates)

        initList()
    }

    fun errorGetData(){
        mainView.error(context.getString(R.string.error_get_data))

    }


    fun getDataForGraph(currencyName: String) {
        Log.d("MyLog", "currencyName = ${currencyName}")
        if (dataForFragment.mapForGraph!=null){
            if(dataForFragment.mapForGraph!!.containsKey(currencyName)){
                dataForFragment.mapForGraph!!.remove(currencyName)
                initList()
            }else{
                repository.getDataForGraph(currencyName)
            }
        }else{
            repository.getDataForGraph(currencyName)
        }


    }





}