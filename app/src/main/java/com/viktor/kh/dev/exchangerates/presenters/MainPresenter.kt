package com.viktor.kh.dev.exchangerates.presenters

import android.content.Context
import android.util.Log
import com.viktor.kh.dev.exchangerates.R
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.data.DataCourses
import com.viktor.kh.dev.exchangerates.data.ExchangeRate
import com.viktor.kh.dev.exchangerates.data.GraphData
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.graph.Graph
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

    private lateinit var dataFragment: DataCourses







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
            updateList()
        }

    }

    fun updateList(){
        if (isShortList){
                initShortList()
            }else{
                initFullList()
            }
       try {
           if (dataFragment.exchangeRates.isNotEmpty()){
               mainView.initList(dataFragment)
           }else{
               errorGetData()
           }
       }catch (e:Exception){
           errorGetData()
       }


    }

    private fun initShortList(){

        try {
            var list = tempCurrencyPojo.exchangeRate.toMutableList()
            if (list.size>0){
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

                if (this::dataFragment.isInitialized){
                    val data = dataFragment

                    //dataFragment = DataCourses(data.date,data.lastNameClicked,data.mapForGraph,newList)
                }
                else{
                    dataFragment = DataCourses(tempCurrencyPojo.date,null,null, newList)
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


              /*  dataFragment = if (this::dataFragment.isInitialized){
                    val data = dataFragment

                    DataCourses(data.date,data.lastNameClicked,data.mapForGraph,list)
                } else{
                    DataCourses(tempCurrencyPojo.date,null,null,list)
                }*/


                isShortList = false

            }
        }catch (e:Exception){
            errorGetData()
        }




    }

    fun setGraph(graphList: List<GraphData>,currencyName: String) {
        val map = mutableMapOf<String,List<GraphData>>()
        if (dataFragment.graphData!=null){
            for (i in dataFragment.graphData!!){
                map.put(i.key,i.value)
            }
        }

        map.put(currencyName,graphList)
        val data = dataFragment

        dataFragment = DataCourses(data.date,currencyName,map,data.exchangeRates)
        updateList()
    }

    fun errorGetData(){
        mainView.error(context.getString(R.string.error_get_data))

    }


    fun getDataForGraph(currencyName: String) {
        //we get grapf for currency name

    }

    fun showMessage(text: String){
        mainView.showMessage(text)
    }


    fun isFirstGraph():Boolean{
        //if we get the graph for the first time it will be true
        return repository.getFirstPreferencesBoolen(context.getString(R.string.preference_first_graph))
    }

    fun setFirstGaph(){
        //we establish that the graph has already been received
        repository.setFirstPreferencesBoolen(context.getString(R.string.preference_first_graph))
    }






}