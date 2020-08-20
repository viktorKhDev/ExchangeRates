package com.viktor.kh.dev.exchangerates.services.network

import android.util.Log
import com.viktor.kh.dev.exchangerates.data.CurrencyPojo
import com.viktor.kh.dev.exchangerates.di.App
import com.viktor.kh.dev.exchangerates.presenters.MainPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkService {
    private val retrofit: Retrofit

    lateinit var mainPresenter: MainPresenter

    @Inject constructor(retrofit: Retrofit){
        this.retrofit = retrofit
        App.component.inject(this)
    }
    fun initMainPresenter(mainPresenter: MainPresenter){
        this.mainPresenter = mainPresenter
    }


    fun getAllCourses(){

        Thread{

        }
        val nbuApi:NbuApi = retrofit.create(NbuApi::class.java)
        var courses:ArrayList<CurrencyPojo>  = ArrayList()
             val call: Call<List<CurrencyPojo>> = nbuApi.getPosts()
           call.enqueue(object :Callback<List<CurrencyPojo>>{
               override fun onResponse(call: Call<List<CurrencyPojo>>, response: Response<List<CurrencyPojo>>) {
                   if(response.isSuccessful){
                       Log.d("MyLog","response.isSuccessful")
                       var tempList: List<CurrencyPojo>? = response.body()

                       if (tempList != null) {
                           for(i in tempList){
                               courses.add(i)
                           }
                           mainPresenter.setCourses(courses)
                       }
                   }

               }

               override fun onFailure(call: Call<List<CurrencyPojo>>, t: Throwable) {
                   Log.d("MyLog","onFailure")
               }

           })
        Log.d("MyLog",courses.size.toString())

    }




}