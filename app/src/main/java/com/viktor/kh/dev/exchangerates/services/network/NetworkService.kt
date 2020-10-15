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


    fun getAllCourses(date:String){

        Log.d("MyLog","date = ${date}")
        val pbApi:PbApi = retrofit.create(PbApi::class.java)

             val call: Call<CurrencyPojo> = pbApi.getPosts(date)
           call.enqueue(object :Callback<CurrencyPojo>{
               override fun onResponse(call: Call<CurrencyPojo>, response: Response<CurrencyPojo>) {
                   if(response.isSuccessful){
                       Log.d("MyLog","response.isSuccessful")
                       var temp: CurrencyPojo? = response.body()

                       if (temp != null) {

                           mainPresenter.setCourses(temp)
                       }
                   }

               }

               override fun onFailure(call: Call<CurrencyPojo>, t: Throwable) {
                   Log.d("MyLog","onFailure  - ${t.message}")


               }

           })


    }




}